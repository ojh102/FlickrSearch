package com.plug.android.utils.rx

import io.reactivex.Scheduler


internal interface SchedulerProvider {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler
}