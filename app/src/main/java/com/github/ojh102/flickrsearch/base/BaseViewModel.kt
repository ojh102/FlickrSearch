package com.github.ojh102.flickrsearch.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

internal abstract class BaseViewModel : ViewModel() {

    protected val disposables: CompositeDisposable by lazy(LazyThreadSafetyMode.NONE){
        CompositeDisposable()
    }

    @CallSuper
    override fun onCleared() {
        disposables.clear()

        super.onCleared()
    }

}