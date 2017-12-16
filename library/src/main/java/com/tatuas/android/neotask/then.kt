package com.tatuas.android.neotask

import android.support.annotation.UiThread
import android.support.annotation.WorkerThread
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.tatuas.android.neotask.NeoTaskAssertions.assertResultNotNull
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

inline fun <R1, R2> Task<R1>.thenMain(crossinline impl: (R1) -> R2): Task<R2> =
        continueWith(NeoTaskExecutors.MAIN_THREAD, impl)

inline fun <R1, R2> Task<R1>.thenAsync(crossinline impl: (R1) -> R2): Task<R2> =
        continueWith(NeoTaskExecutors.ASYNC_DEFAULT, impl)

inline fun <R1, R2> Task<R1>.thenBlocking(crossinline impl: (R1) -> R2): Task<R2> =
        continueWith(NeoTaskExecutors.CURRENT, impl)

@UiThread
inline fun <R1, R2> Task<R1>.then(crossinline generateNextTask: (R1) -> Task<R2>): Task<R2> =
        continueWithTask(NeoTaskExecutors.getContinueWithTaskExecutor(), generateNextTask)

/**
 * For background execution such as IntentService.
 * If call these methods from ui thread, it will be an error.
 */
@WorkerThread
inline fun <R1, R2> Task<R1>.thenAwait(crossinline generateNextTask: (R1) -> Task<R2>): Task<R2> =
        thenAwait(generateNextTask, 180, TimeUnit.SECONDS)

@WorkerThread
inline fun <R1, R2> Task<R1>.thenAwait(crossinline generateNextTask: (R1) -> Task<R2>, timeout: Long,
                                       timeUnit: TimeUnit): Task<R2> =
        thenBlocking { Tasks.await(generateNextTask.invoke(it), timeout, timeUnit) }

/**
 * This methods need not be used normally.
 */
inline fun <R1, R2> Task<R1>.continueWithTask(executor: Executor,
                                              crossinline generateNextTask: (R1) -> Task<R2>): Task<R2> =
        continueWithTask(executor, Continuation {
            if (it.isSuccessful) {
                assertResultNotNull(it)
                generateNextTask(it.result)
            } else {
                throw it.exception ?: NeoTaskNullThrowableException()
            }
        })

/**
 * This methods need not be used normally.
 */
inline fun <R1, R2> Task<R1>.continueWith(executor: Executor, crossinline impl: (R1) -> R2): Task<R2> =
        continueWithTask(executor, Continuation {
            if (it.isSuccessful) {
                assertResultNotNull(it)
                Tasks.call(executor, Callable { impl.invoke(result) })
            } else {
                throw it.exception ?: NeoTaskNullThrowableException()
            }
        })
