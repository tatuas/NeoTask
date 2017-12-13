package com.tatuas.android.neotask

import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.tatuas.android.neotask.NeoTaskAssertions.assertResultNotNull
import java.util.concurrent.Callable
import java.util.concurrent.Executor

inline fun <R1, R2> Task<R1>.then(crossinline generateNextTask: (R1) -> Task<R2>): Task<R2> =
        then(NeoTaskExecutors.THEN_DEFAULT, generateNextTask)

inline fun <R1, R2> Task<R1>.thenBlocking(crossinline generateNextTask: (R1) -> Task<R2>): Task<R2> =
        then(NeoTaskExecutors.CURRENT, generateNextTask)

inline fun <R1, R2> Task<R1>.then(executor: Executor,
                                  crossinline generateNextTask: (R1) -> Task<R2>): Task<R2> =
        continueWithTask(executor, Continuation {
            if (it.isSuccessful) {
                assertResultNotNull(it)
                generateNextTask(it.result)
            } else {
                throw it.exception ?: NeoTaskNullThrowableException()
            }
        })

inline fun <R1, R2> Task<R1>.thenCallable(crossinline generateNextCallable: (R1) -> Callable<R2>): Task<R2> =
        thenCallable(NeoTaskExecutors.THEN_DEFAULT, generateNextCallable)

inline fun <R1, R2> Task<R1>.thenCallableBlocking(crossinline generateNextCallable: (R1) -> Callable<R2>): Task<R2> =
        thenCallable(NeoTaskExecutors.CURRENT, generateNextCallable)

inline fun <R1, R2> Task<R1>.thenCallable(executor: Executor,
                                          crossinline generateNextCallable: (R1) -> Callable<R2>): Task<R2> =
        continueWithTask(executor, Continuation {
            if (it.isSuccessful) {
                assertResultNotNull(it)
                NeoTask.blocking(generateNextCallable(it.result))
            } else {
                throw it.exception ?: NeoTaskNullThrowableException()
            }
        })
