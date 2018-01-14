package com.tatuas.android.neotask

import java.util.concurrent.TimeUnit

data class AwaitTimeOut(val time: Long, val unit: TimeUnit) {
    companion object {
        @JvmField
        val DEFAULT = minutes(3)

        fun nanos(time: Long) = AwaitTimeOut(time, TimeUnit.NANOSECONDS)
        fun micros(time: Long) = AwaitTimeOut(time, TimeUnit.MICROSECONDS)
        fun millis(time: Long) = AwaitTimeOut(time, TimeUnit.MILLISECONDS)
        fun seconds(time: Long) = AwaitTimeOut(time, TimeUnit.SECONDS)
        fun minutes(time: Long) = AwaitTimeOut(time, TimeUnit.MINUTES)
        fun hours(time: Long) = AwaitTimeOut(time, TimeUnit.HOURS)
        fun days(time: Long) = AwaitTimeOut(time, TimeUnit.DAYS)
    }
}
