package com.tatuas.android.neotask

import com.google.android.gms.tasks.TaskCompletionSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NeoTaskAssertionsUnitTest {

    @Test
    fun check() {
        val taskSource = TaskCompletionSource<String>()
        taskSource.setResult(null)

        try {
            NeoTaskAssertions.assertResultNotNull(taskSource.task)
        } catch (e: Exception) {
            assertThat(e::class.java).isEqualTo(NeoTaskNullResultException::class.java)
        }
    }
}
