package com.tatuas.android.neotasksample

import com.tatuas.android.neotasksample.Utils.log
import java.util.concurrent.Callable

object SampleCallable {

    @Throws(InterruptedException::class)
    private fun sleep() {
        Thread.sleep(3000)
    }

    class LongCallable : Callable<Long> {
        @Throws(Exception::class)
        override fun call(): Long {
            log("LongCallable:start: ${Thread.currentThread()}")
            sleep()
            log("LongCallable:finish: ${Thread.currentThread()}")
            return System.currentTimeMillis()
        }
    }

    class Long2Callable(private val value: Long) : Callable<Long> {
        @Throws(Exception::class)
        override fun call(): Long {
            log("LongCallable2:start: ${Thread.currentThread()}")
            sleep()
            log("LongCallable2:finish: ${Thread.currentThread()}")
            return System.currentTimeMillis() + value
        }
    }

    class StringCallable : Callable<String> {
        @Throws(Exception::class)
        override fun call(): String {
            log("StringCallable:start: ${Thread.currentThread()}")
            sleep()
            log("StringCallable:finish: ${Thread.currentThread()}")
            return "string"
        }
    }

    class StringCallable2(private val value: String) : Callable<String> {
        @Throws(Exception::class)
        override fun call(): String {
            log("StringCallable2:start: ${Thread.currentThread()}")
            sleep()
            log("StringCallable2:finish: ${Thread.currentThread()}")
            return value + "string"
        }
    }

    class ErrorCallable : Callable<String> {
        @Throws(Exception::class)
        override fun call(): String {
            log("ErrorCallable:start: ${Thread.currentThread()}")
            sleep()
            log("ErrorCallable:stop: ${Thread.currentThread()}")
            throw Exception("error")
        }
    }

    class VoidCallable : Callable<Void> {
        @Throws(Exception::class)
        override fun call(): Void? {
            log("VoidCallable:start: ${Thread.currentThread()}")
            sleep()
            log("VoidCallable:stop: ${Thread.currentThread()}")
            return null
        }
    }
}
