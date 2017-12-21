package com.tatuas.android.neotask

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task

fun <R> Task<R>.addOnBlockingCompleteListener(func: (Task<R>) -> Unit) {
    addOnCompleteListener(NeoTaskExecutors.CURRENT, OnCompleteListener { func.invoke(it) })
}
