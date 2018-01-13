package com.tatuas.android.neotask

import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import java.util.concurrent.Callable

fun Task<Void>.toBoolean(): Task<Boolean> {
    val executor = NeoTaskExecutors.getToBooleanExecutor()
    return continueWithTask(executor, Continuation { Tasks.call(executor, Callable { true }) })
}
