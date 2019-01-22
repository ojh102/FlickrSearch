package com.github.ojh102.flickrsearch.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.github.ojh102.flickrsearch.R
import com.github.ojh102.flickrsearch.base.BaseViewModel
import com.github.ojh102.flickrsearch.data.remote.response.FlickrPhoto
import com.github.ojh102.flickrsearch.repository.RemoteRepository
import com.github.ojh102.flickrsearch.ui.search.keyword.KeywordItem
import com.github.ojh102.flickrsearch.utils.ResourcesProvider
import com.github.ojh102.flickrsearch.utils.extension.subscribeOf
import com.github.ojh102.flickrsearch.utils.extension.throttleClick
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import javax.inject.Inject

internal class SearchActivityViewModel @Inject constructor(
        resourcesProvider: ResourcesProvider,
        private val remoteRepository: RemoteRepository

) : BaseViewModel() {

    val keywordList: MutableLiveData<List<KeywordItem>> = MutableLiveData()
    val selectedKeyword: MutableLiveData<KeywordItem> = MutableLiveData()

    val photoList: MutableLiveData<PagedList<FlickrPhoto>> = MutableLiveData()

    private val actionRelay = PublishRelay.create<SearchAction>()
    private val stateRelay = PublishRelay.create<SearchState>()

    private val clickAction = ofAction().ofType<SearchAction.Click>().throttleClick()

    init {
        disposables.addAll(
                clickAction
                        .ofType<SearchAction.Click.Keyword>()
                        .doOnNext { toState(SearchState.SelectedKeyword(it.keywordItem)) }
                        .switchMap { remoteRepository.search(it.keywordItem.keyword) }
                        .subscribeOf(onNext = { toState(SearchState.SearchedPhotoList(it)) }),

                ofState()
                        .ofType<SearchState.KeywordList>()
                        .subscribeOf(onNext = { keywordList.postValue(it.keywordList) }),

                ofState()
                        .ofType<SearchState.SelectedKeyword>()
                        .subscribeOf(onNext = { selectedKeyword.postValue(it.keyword) }),

                ofState()
                        .ofType<SearchState.SearchedPhotoList>()
                        .subscribeOf(onNext = { photoList.postValue(it.photoPagedList) })
        )

        val keywords = resourcesProvider.getStringArray(R.array.keywords).map { KeywordItem(it) }

        toState(SearchState.KeywordList(keywords))

        toAction(SearchAction.Click.Keyword(keywords.first()))
    }

    fun toState(state: SearchState) = stateRelay.accept(state)
    fun ofState(): Observable<SearchState> = stateRelay

    fun toAction(action: SearchAction) = actionRelay.accept(action)
    fun ofAction(): Observable<SearchAction> = actionRelay

}