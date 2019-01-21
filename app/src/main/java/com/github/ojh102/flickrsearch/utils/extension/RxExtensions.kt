package com.github.ojh102.flickrsearch.utils.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.*
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import timber.log.Timber
import java.util.concurrent.TimeUnit

private val onNextStub: (Any) -> Unit = {}
private val onSuccessStub: (Any) -> Unit = {}
private val onCompleteStub: () -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = {
    Timber.e("Rx Error - $it")
    throw it
}


@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
internal fun <T : Any> Flowable<T>.subscribeOf(
        onNext: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub

): Disposable = subscribe(onNext, onError, onComplete)


@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
internal fun <T : Any> Observable<T>.subscribeOf(
        onNext: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub

): Disposable = subscribe(onNext, onError, onComplete)


@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
internal fun <T : Any> Single<T>.subscribeOf(
        onSuccess: (T) -> Unit = onSuccessStub,
        onError: (Throwable) -> Unit = onErrorStub

): Disposable = subscribe(onSuccess, onError)


@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
internal fun <T : Any> Maybe<T>.subscribeOf(
        onSuccess: (T) -> Unit = onSuccessStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
): Disposable = subscribe(onSuccess, onError, onComplete)


@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
internal fun Completable.subscribeOf(
        onComplete: () -> Unit = onCompleteStub,
        onError: (Throwable) -> Unit = onErrorStub
): Disposable = subscribe(onComplete, onError)

internal fun <T> Flowable<T>.toLiveData(): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher(this)
}

internal fun <T> Observable<T>.toLiveData(backPressureStrategy: BackpressureStrategy = BackpressureStrategy.LATEST): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher(this.toFlowable(backPressureStrategy))
}

internal fun <T> Single<T>.toLiveData(): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher(this.toFlowable())
}

internal fun <T> Maybe<T>.toLiveData(): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher(this.toFlowable())
}

internal fun <T> Completable.toLiveData(): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher(this.toFlowable())
}

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
internal fun <T, U> Observable<T>.withLatestFromSecond(other: ObservableSource<U>): Observable<U> = withLatestFrom(other, BiFunction { _, u -> u })

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.COMPUTATION)
internal fun <T> Observable<T>.throttleClick(): Observable<T> = throttleFirst(300, TimeUnit.MILLISECONDS)