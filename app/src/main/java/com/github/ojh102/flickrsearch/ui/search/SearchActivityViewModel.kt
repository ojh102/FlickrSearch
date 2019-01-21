package com.github.ojh102.flickrsearch.ui.search

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.github.ojh102.flickrsearch.R
import com.github.ojh102.flickrsearch.base.BaseViewModel
import com.github.ojh102.flickrsearch.data.remote.response.FlickrPhoto
import com.github.ojh102.flickrsearch.repository.RemoteRepository
import com.github.ojh102.flickrsearch.ui.search.keyword.KeywordItem
import com.github.ojh102.flickrsearch.utils.extension.subscribeOf
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import javax.inject.Inject

internal class SearchActivityViewModel @Inject constructor(
        private val resources: Resources,
        private val remoteRepository: RemoteRepository

) : BaseViewModel() {

    val keywordList: MutableLiveData<List<KeywordItem>> = MutableLiveData()
    val selectedKeyword: MutableLiveData<KeywordItem> = MutableLiveData()
    val photoList: MutableLiveData<PagedList<FlickrPhoto>> = MutableLiveData()

    private val actionRelay = PublishRelay.create<SearchAction>()

    init {
        disposables.addAll(
                ofAction()
                        .ofType<SearchAction.Select.Keyword>()
                        .doOnNext { selectedKeyword.postValue(it.keywordItem) }
                        .switchMap { remoteRepository.search(it.keywordItem.keyword) }
//                        .map {
//                            it.map { photo ->
//                                PhotoItem(title = photo.title, url = photo.url)
//                            }
//                        }
                        .subscribeOf(onNext = { photoList.postValue(it) })
        )

        val keywords = resources.getStringArray(R.array.keywords).map { KeywordItem(it) }

        keywordList.postValue(keywords)

        toAction(SearchAction.Select.Keyword(keywords.first()))
    }

    fun toAction(action: SearchAction) = actionRelay.accept(action)
    fun ofAction(): Observable<SearchAction> = actionRelay

}