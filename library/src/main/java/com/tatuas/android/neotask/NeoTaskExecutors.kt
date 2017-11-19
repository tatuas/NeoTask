package com.tatuas.android.neotask

import com.google.android.gms.tasks.TaskExecutors
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object NeoTaskExecutors {

    @JvmField
    val THEN_DEFAULT: Executor = Executors.newFixedThreadPool(2)

    @JvmField
    val PARALLEL_DEFAULT: Executor = Executors.newFixedThreadPool(2)

    @JvmField
    val ASYNC_DEFAULT: Executor = Executors.newCachedThreadPool()

    @JvmField
    val CURRENT: Executor = Executor { command -> command.run() }

    @JvmField
    val MAIN_THREAD: Executor = TaskExecutors.MAIN_THREAD
}
