package com.tatuas.android.neotask

import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.tatuas.android.neotask.NeoTaskAssertions.assertResultNotNull
import java.util.concurrent.Callable
import java.util.concurrent.Executor

object NeoTask {

    fun <R> callAsync(callable: Callable<R>): Task<R> = Tasks.call(
            NeoTaskExecutors.ASYNC_DEFAULT, callable)

    fun <R> callBlocking(callable: Callable<R>): Task<R> = Tasks.call(
            NeoTaskExecutors.CURRENT, callable)

    fun <R1, R2> parallel(task1: Task<R1>,
                          task2: Task<R2>): Task<Pair<R1, R2>> =
            parallel(NeoTaskExecutors.PARALLEL_DEFAULT, task1, task2)

    fun <R1, R2, R3> parallel(task1: Task<R1>,
                              task2: Task<R2>,
                              task3: Task<R3>): Task<Triple<R1, R2, R3>> =
            parallel(NeoTaskExecutors.PARALLEL_DEFAULT, task1, task2, task3)

    fun <R1, R2, R3, R4> parallel(task1: Task<R1>,
                                  task2: Task<R2>,
                                  task3: Task<R3>,
                                  task4: Task<R4>): Task<NeoTaskQuartet<R1, R2, R3, R4>> =
            parallel(NeoTaskExecutors.PARALLEL_DEFAULT, task1, task2, task3, task4)

    fun <R1, R2, R3, R4, R5> parallel(task1: Task<R1>,
                                      task2: Task<R2>,
                                      task3: Task<R3>,
                                      task4: Task<R4>,
                                      task5: Task<R5>): Task<NeoTaskQuintet<R1, R2, R3, R4, R5>> =
            parallel(NeoTaskExecutors.PARALLEL_DEFAULT, task1, task2, task3, task4, task5)

    fun <R1, R2> parallel(executor: Executor,
                          task1: Task<R1>,
                          task2: Task<R2>): Task<Pair<R1, R2>> =
            Tasks.whenAll(task1, task2)
                    .continueWith(executor, Continuation {
                        assertResultNotNull(task1, task2)
                        Pair(task1.result, task2.result)
                    })

    fun <R1, R2, R3> parallel(executor: Executor,
                              task1: Task<R1>,
                              task2: Task<R2>,
                              task3: Task<R3>): Task<Triple<R1, R2, R3>> =
            Tasks.whenAll(task1, task2, task3)
                    .continueWith(executor, Continuation {
                        assertResultNotNull(task1, task2, task3)
                        Triple(task1.result, task2.result, task3.result)
                    })

    fun <R1, R2, R3, R4> parallel(executor: Executor,
                                  task1: Task<R1>,
                                  task2: Task<R2>,
                                  task3: Task<R3>,
                                  task4: Task<R4>): Task<NeoTaskQuartet<R1, R2, R3, R4>> =
            Tasks.whenAll(task1, task2, task3, task4)
                    .continueWith(executor, Continuation {
                        assertResultNotNull(task1, task2, task3, task4)
                        NeoTaskQuartet(task1.result, task2.result, task3.result, task4.result)
                    })

    fun <R1, R2, R3, R4, R5> parallel(executor: Executor,
                                      task1: Task<R1>,
                                      task2: Task<R2>,
                                      task3: Task<R3>,
                                      task4: Task<R4>,
                                      task5: Task<R5>): Task<NeoTaskQuintet<R1, R2, R3, R4, R5>> =
            Tasks.whenAll(task1, task2, task3, task4, task5)
                    .continueWith(executor, Continuation {
                        assertResultNotNull(task1, task2, task3, task4, task5)
                        NeoTaskQuintet(task1.result, task2.result, task3.result, task4.result, task5.result)
                    })
}
