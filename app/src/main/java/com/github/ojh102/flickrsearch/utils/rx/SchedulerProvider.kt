package com.github.ojh102.flickrsearch.utils.rx

import io.reactivex.Scheduler


internal interface SchedulerProvider {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler
}