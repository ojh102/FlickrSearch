package com.github.ojh102.flickersearch.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

internal abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy(LazyThreadSafetyMode.NONE){
        CompositeDisposable()
    }

    @CallSuper
    override fun onCleared() {
        compositeDisposable.clear()

        super.onCleared()
    }

}