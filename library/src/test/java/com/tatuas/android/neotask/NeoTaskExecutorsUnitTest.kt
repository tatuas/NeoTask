package com.tatuas.android.neotask

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NeoTaskExecutorsUnitTest {

    @Test
    fun check() {
        assertThat(NeoTaskExecutors.ASYNC_DEFAULT).isNotEqualTo(null)
        assertThat(NeoTaskExecutors.CURRENT).isNotEqualTo(null)
        assertThat(NeoTaskExecutors.MAIN_THREAD).isNotEqualTo(null)
        assertThat(NeoTaskExecutors.PARALLEL_DEFAULT).isNotEqualTo(null)
        assertThat(NeoTaskExecutors.THEN_ASYNC_DEFAULT).isNotEqualTo(null)
        assertThat(NeoTaskExecutors.getThenExecutor()).isNotEqualTo(null)
    }
}
