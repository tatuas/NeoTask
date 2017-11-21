package com.tatuas.android.neotask

import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import java.util.concurrent.Callable

fun Task<Void>.toBoolean(): Task<Boolean> = continueWithTask(
        NeoTaskExecutors.THEN_DEFAULT, Continuation { NeoTask.callBlocking(Callable { true }) })

fun Task<Void>.toBooleanBlocking(): Task<Boolean> = continueWithTask(
        NeoTaskExecutors.CURRENT, Continuation { NeoTask.callBlocking(Callable { true }) })
