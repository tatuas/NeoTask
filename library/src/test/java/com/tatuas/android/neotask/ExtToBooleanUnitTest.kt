package com.tatuas.android.neotask

import com.google.android.gms.tasks.Tasks
import com.tatuas.android.neotask.util.assertEquals
import com.tatuas.android.neotask.util.createVoidTask
import org.assertj.core.api.Assertions
import org.awaitility.Awaitility
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ExtToBooleanUnitTest {

    @Test
    fun checkToBooleanOnMainThread() {
        assertEquals(true, createVoidTask().toBoolean())
    }

    @Test
    fun checkToBooleanOnBackgroundThread() {
        Awaitility.await()
                .atMost(10L, TimeUnit.SECONDS)
                .until {
                    true.also {
                        Assertions.assertThat(true)
                                .isEqualTo(Tasks.await(createVoidTask().toBoolean()))
                    }
                }
    }
}
