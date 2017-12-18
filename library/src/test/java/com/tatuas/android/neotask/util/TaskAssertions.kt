package com.tatuas.android.neotask.util

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import org.assertj.core.api.Assertions
import org.awaitility.Awaitility
import java.util.concurrent.TimeUnit

internal fun <T> assertEquals(expect: T, task: Task<T>) {
    executeAsync {
        val result = Tasks.await(task)
        true.also { Assertions.assertThat(expect).isEqualTo(result) }
    }
}

private fun executeAsync(assertion: () -> Boolean) {
    Awaitility.await()
            .atMost(10L, TimeUnit.SECONDS)
            .until { true.also { assertion.invoke() } }
}
