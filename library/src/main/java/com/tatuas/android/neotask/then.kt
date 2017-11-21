package com.tatuas.android.neotask

import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.tatuas.android.neotask.NeoTaskAssertions.assertResultNotNull
import java.util.concurrent.Callable

inline fun <R1, R2> Task<R1>.then(crossinline generateNextTask: (R1) -> Task<R2>): Task<R2> {
    return continueWithTask(NeoTaskExecutors.THEN_DEFAULT, Continuation {
        if (it.isSuccessful) {
            assertResultNotNull(it)
            generateNextTask(it.result)
        } else {
            throw it.exception ?: NeoTaskNullThrowableException()
        }
    })
}

inline fun <R1, R2> Task<R1>.thenCallable(crossinline generateNextCallable: (R1) -> Callable<R2>): Task<R2> {
    return continueWithTask(NeoTaskExecutors.THEN_DEFAULT, Continuation {
        if (it.isSuccessful) {
            assertResultNotNull(it)
            NeoTask.callBlocking(generateNextCallable(it.result))
        } else {
            throw it.exception ?: NeoTaskNullThrowableException()
        }
    })
}

inline fun <R1, R2> Task<R1>.thenBlocking(crossinline generateNextTask: (R1) -> Task<R2>): Task<R2> {
    return continueWithTask(NeoTaskExecutors.CURRENT, Continuation {
        if (it.isSuccessful) {
            assertResultNotNull(it)
            generateNextTask(it.result)
        } else {
            throw it.exception ?: NeoTaskNullThrowableException()
        }
    })
}

inline fun <R1, R2> Task<R1>.thenCallableBlocking(crossinline generateNextCallable: (R1) -> Callable<R2>): Task<R2> {
    return continueWithTask(NeoTaskExecutors.CURRENT, Continuation {
        if (it.isSuccessful) {
            assertResultNotNull(it)
            NeoTask.callBlocking(generateNextCallable(it.result))
        } else {
            throw it.exception ?: NeoTaskNullThrowableException()
        }
    })
}
