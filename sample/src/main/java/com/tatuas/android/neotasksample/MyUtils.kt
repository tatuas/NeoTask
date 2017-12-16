package com.tatuas.android.neotasksample

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object MyUtils {

    private const val TAG = "NeoTaskSample"

    fun log(message: String) {
        Log.d(TAG, message)
    }

    @Throws(InterruptedException::class)
    fun sleep() {
        Thread.sleep(3000)
    }

    @JvmField
    val VOID_EXECUTOR: Executor = Executors.newSingleThreadExecutor()

    private class VoidCallable : Callable<Void> {
        @Throws(Exception::class)
        override fun call(): Void? {
            MyUtils.log("VoidCallable:Start: ${Thread.currentThread()}")
            MyUtils.sleep()
            MyUtils.log("VoidCallable:Stop: ${Thread.currentThread()}")
            return null
        }
    }

    fun createVoidTask(): Task<Void> = Tasks.call(VOID_EXECUTOR, VoidCallable())
}
