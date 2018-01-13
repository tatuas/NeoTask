package com.tatuas.android.neotask

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NeoTaskNullThrowableExceptionUnitTest {

    @Test
    fun check() {
        assertThat(NeoTaskNullThrowableException()::class.java)
                .isEqualTo(NeoTaskNullThrowableException::class.java)
    }
}
