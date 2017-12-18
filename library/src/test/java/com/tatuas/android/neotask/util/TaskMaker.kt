package com.tatuas.android.neotask.util

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import java.util.concurrent.Callable
import java.util.concurrent.Executors

private val executor = Executors.newCachedThreadPool()

private class VoidCallable : Callable<Void> {
    @Throws(Exception::class)
    override fun call(): Void? {
        Thread.sleep(3000)
        return null
    }
}

private class StringCallable(val additional: String) : Callable<String> {
    @Throws(Exception::class)
    override fun call(): String {
        return "result" + additional
    }
}

fun createVoidTask(): Task<Void> {
    return Tasks.call(executor, VoidCallable())
}

fun createStringTask(additional: String = ""): Task<String> {
    return Tasks.call(executor, StringCallable(additional))
}
