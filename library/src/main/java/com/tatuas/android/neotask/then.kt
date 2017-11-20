package com.tatuas.android.neotask

import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.tasks.Tasks.await
import com.tatuas.android.neotask.NeoTaskUtils.assertResultNotNull
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

inline fun <R1, R2> Task<R1>.then(crossinline generateNextTask: (R1) -> Task<R2>): Task<R2> {
    return continueWith(NeoTaskExecutors.THEN_DEFAULT, Continuation {
        if (it.isSuccessful) {
            assertResultNotNull(it)
            await(generateNextTask(it.result), NeoTaskTimeout.TIME, NeoTaskTimeout.UNIT)
        } else {
            throw it.exception ?: NeoTaskNullThrowableException()
        }
    })
}

inline fun <R1, R2> Task<R1>.then(crossinline generateNextTask: (R1) -> Task<R2>,
                                  executor: Executor = NeoTaskExecutors.THEN_DEFAULT,
                                  timeout: Long = NeoTaskTimeout.TIME,
                                  timeUnit: TimeUnit = NeoTaskTimeout.UNIT): Task<R2> {
    return continueWith(executor, Continuation {
        if (it.isSuccessful) {
            assertResultNotNull(it)
            await(generateNextTask(it.result), timeout, timeUnit)
        } else {
            throw it.exception ?: NeoTaskNullThrowableException()
        }
    })
}

inline fun <R1, R2> Task<R1>.thenCallable(crossinline generateNextCallable: (R1) -> Callable<R2>): Task<R2> {
    return continueWith(NeoTaskExecutors.THEN_DEFAULT, Continuation {
        if (it.isSuccessful) {
            assertResultNotNull(it)
            await(Tasks.call(NeoTaskExecutors.CURRENT, generateNextCallable(it.result)),
                    NeoTaskTimeout.TIME, NeoTaskTimeout.UNIT)
        } else {
            throw it.exception ?: NeoTaskNullThrowableException()
        }
    })
}

inline fun <R1, R2> Task<R1>.thenCallable(crossinline generateNextCallable: (R1) -> Callable<R2>,
                                          executor: Executor = NeoTaskExecutors.THEN_DEFAULT,
                                          timeout: Long = NeoTaskTimeout.TIME,
                                          timeUnit: TimeUnit = NeoTaskTimeout.UNIT): Task<R2> {
    return continueWith(executor, Continuation {
        if (it.isSuccessful) {
            assertResultNotNull(it)
            await(Tasks.call(NeoTaskExecutors.CURRENT, generateNextCallable(it.result)),
                    timeout, timeUnit)
        } else {
            throw it.exception ?: NeoTaskNullThrowableException()
        }
    })
}

inline fun <R1, R2> Task<R1>.thenBlocking(crossinline generateNextTask: (R1) -> Task<R2>): Task<R2> {
    return continueWith(NeoTaskExecutors.CURRENT, Continuation {
        if (it.isSuccessful) {
            assertResultNotNull(it)
            await(generateNextTask(it.result), NeoTaskTimeout.TIME, NeoTaskTimeout.UNIT)
        } else {
            throw it.exception ?: NeoTaskNullThrowableException()
        }
    })
}

inline fun <R1, R2> Task<R1>.thenBlocking(crossinline generateNextTask: (R1) -> Task<R2>,
                                          timeout: Long = NeoTaskTimeout.TIME,
                                          timeUnit: TimeUnit = NeoTaskTimeout.UNIT): Task<R2> {
    return continueWith(NeoTaskExecutors.CURRENT, Continuation {
        if (it.isSuccessful) {
            assertResultNotNull(it)
            await(generateNextTask(it.result), timeout, timeUnit)
        } else {
            throw it.exception ?: throw NeoTaskNullThrowableException()
        }
    })
}

inline fun <R1, R2> Task<R1>.thenCallableBlocking(crossinline generateNextCallable: (R1) -> Callable<R2>): Task<R2> {
    return continueWith(NeoTaskExecutors.CURRENT, Continuation {
        if (it.isSuccessful) {
            assertResultNotNull(it)
            await(NeoTask.callBlocking(generateNextCallable(it.result)),
                    NeoTaskTimeout.TIME, NeoTaskTimeout.UNIT)
        } else {
            throw it.exception ?: NeoTaskNullThrowableException()
        }
    })
}

inline fun <R1, R2> Task<R1>.thenCallableBlocking(crossinline generateNextCallable: (R1) -> Callable<R2>,
                                                  timeout: Long = NeoTaskTimeout.TIME,
                                                  timeUnit: TimeUnit = NeoTaskTimeout.UNIT): Task<R2> {
    return continueWith(NeoTaskExecutors.CURRENT, Continuation {
        if (it.isSuccessful) {
            assertResultNotNull(it)
            await(NeoTask.callBlocking(generateNextCallable(it.result)), timeout, timeUnit)
        } else {
            throw it.exception ?: NeoTaskNullThrowableException()
        }
    })
}
