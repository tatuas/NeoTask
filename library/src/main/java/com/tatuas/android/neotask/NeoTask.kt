package com.tatuas.android.neotask

import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.tatuas.android.neotask.NeoTaskAssertions.assertResultNotNull
import java.util.concurrent.Callable
import java.util.concurrent.Executor

object NeoTask {

    /**
     * Task creation
     */
    private fun <R> async(callable: Callable<R>): Task<R> = Tasks.call(NeoTaskExecutors.ASYNC_DEFAULT, callable)

    private fun <R> blocking(callable: Callable<R>): Task<R> = Tasks.call(NeoTaskExecutors.CURRENT, callable)

    private fun <R> main(callable: Callable<R>): Task<R> = Tasks.call(callable)

    fun <R> async(func: () -> R): Task<R> = async(Callable<R> { func.invoke() })

    fun <R> blocking(func: () -> R): Task<R> = blocking(Callable<R> { func.invoke() })

    fun <R> main(func: () -> R): Task<R> = main(Callable<R> { func.invoke() })

    /**
     * Wrapped await
     */
    fun <R> await(task: Task<R>, awaitTimeout: AwaitTimeout = AwaitTimeout.DEFAULT): R {
        return Tasks.await(task, awaitTimeout.time, awaitTimeout.unit)
    }

    /**
     * Sequential await execution
     */
    fun <T1, R> awaitSequential(firstTask: Task<T1>, secondTask: (T1) -> Task<R>,
                                awaitTimeout: AwaitTimeout = AwaitTimeout.DEFAULT): R {
        val t1 = await(firstTask, awaitTimeout)
        return await(secondTask.invoke(t1), awaitTimeout)
    }

    fun <T1, T2, R> awaitSequential(firstTask: Task<T1>, secondTask: (T1) -> Task<T2>,
                                    thirdTask: (T2) -> Task<R>,
                                    awaitTimeout: AwaitTimeout = AwaitTimeout.DEFAULT): R {
        val t1 = await(firstTask, awaitTimeout)
        val t2 = await(secondTask.invoke(t1), awaitTimeout)
        return await(thirdTask.invoke(t2), AwaitTimeout.DEFAULT)
    }

    fun <T1, T2, T3, R> awaitSequential(firstTask: Task<T1>, secondTask: (T1) -> Task<T2>,
                                        thirdTask: (T2) -> Task<T3>, fourthTask: (T3) -> Task<R>,
                                        awaitTimeout: AwaitTimeout = AwaitTimeout.DEFAULT): R {
        val t1 = await(firstTask, awaitTimeout)
        val t2 = await(secondTask.invoke(t1), awaitTimeout)
        val t3 = await(thirdTask.invoke(t2), awaitTimeout)
        return await(fourthTask.invoke(t3), AwaitTimeout.DEFAULT)
    }

    fun <T1, T2, T3, T4, R> awaitSequential(firstTask: Task<T1>, secondTask: (T1) -> Task<T2>,
                                            thirdTask: (T2) -> Task<T3>, fourthTask: (T3) -> Task<T4>,
                                            fifthTask: (T4) -> Task<R>,
                                            awaitTimeout: AwaitTimeout = AwaitTimeout.DEFAULT): R {
        val t1 = await(firstTask, awaitTimeout)
        val t2 = await(secondTask.invoke(t1), awaitTimeout)
        val t3 = await(thirdTask.invoke(t2), awaitTimeout)
        val t4 = await(fourthTask.invoke(t3), awaitTimeout)
        return await(fifthTask.invoke(t4), AwaitTimeout.DEFAULT)
    }

    /**
     * Parallel execution
     */
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
