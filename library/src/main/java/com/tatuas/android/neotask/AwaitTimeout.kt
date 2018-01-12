package com.tatuas.android.neotask

import java.util.concurrent.TimeUnit

data class AwaitTimeout(val time: Long, val unit: TimeUnit) {
    companion object {
        @JvmField
        val DEFAULT = minutes(3)

        fun nanos(time: Long) = AwaitTimeout(time, TimeUnit.NANOSECONDS)
        fun micros(time: Long) = AwaitTimeout(time, TimeUnit.MICROSECONDS)
        fun millis(time: Long) = AwaitTimeout(time, TimeUnit.MILLISECONDS)
        fun seconds(time: Long) = AwaitTimeout(time, TimeUnit.SECONDS)
        fun minutes(time: Long) = AwaitTimeout(time, TimeUnit.MINUTES)
        fun hours(time: Long) = AwaitTimeout(time, TimeUnit.HOURS)
        fun days(time: Long) = AwaitTimeout(time, TimeUnit.DAYS)
    }
}
