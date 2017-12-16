package com.tatuas.android.neotask

import android.os.Looper
import com.google.android.gms.tasks.TaskExecutors
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object NeoTaskExecutors {

    @JvmField
    val MAIN_THREAD: Executor = TaskExecutors.MAIN_THREAD

    @JvmField
    val CURRENT: Executor = Executor { command -> command.run() }

    @JvmField
    val ASYNC_DEFAULT: Executor = Executors.newCachedThreadPool()

    @JvmField
    val PARALLEL_DEFAULT: Executor = Executors.newFixedThreadPool(2)

    @JvmField
    val CONTINUE_WITH_TASK_DEFAULT: Executor = Executors.newFixedThreadPool(2)

    /**
     * If this method is called from the UI thread, it automatically switches to background execution.
     * Otherwise, it will run on the called Thread.
     */
    fun getContinueWithTaskExecutor(): Executor = if (Thread.currentThread() == Looper.getMainLooper().thread) {
        CONTINUE_WITH_TASK_DEFAULT
    } else {
        CURRENT
    }
}
