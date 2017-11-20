package com.tatuas.android.neotask

import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import java.util.concurrent.Callable

fun Task<Void>.toBoolean(): Task<Boolean> {
    return continueWith(NeoTaskExecutors.THEN_DEFAULT, Continuation {
        if (it.isSuccessful) {
            Tasks.await(NeoTask.callBlocking(Callable { true }), NeoTaskTimeout.TIME, NeoTaskTimeout.UNIT)
        } else {
            throw it.exception ?: NeoTaskNullThrowableException()
        }
    })
}

fun Task<Void>.toBooleanBlocking(): Task<Boolean> {
    return continueWith(NeoTaskExecutors.CURRENT, Continuation {
        if (it.isSuccessful) {
            Tasks.await(NeoTask.callBlocking(Callable { true }), NeoTaskTimeout.TIME, NeoTaskTimeout.UNIT)
        } else {
            throw it.exception ?: NeoTaskNullThrowableException()
        }
    })
}
