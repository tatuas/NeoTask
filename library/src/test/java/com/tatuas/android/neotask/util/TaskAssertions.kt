package com.tatuas.android.neotask.util

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import org.assertj.core.api.Assertions
import org.awaitility.Awaitility
import java.util.concurrent.TimeUnit

fun <T> assertEquals(expect: T, actualTask: Task<T>) {
    assertEquals(expect, { Tasks.await(actualTask) })
}

fun <T> assertEquals(expect: T, actualTaskInline: () -> T) {
    Awaitility.await()
            .atMost(10L, TimeUnit.SECONDS)
            .until {
                true.also {
                    Assertions.assertThat(actualTaskInline.invoke()).isEqualTo(expect)
                }
            }
}
