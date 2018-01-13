package com.tatuas.android.neotask

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NeoTaskQuartetUnitTest {

    @Test
    fun check() {
        val item = NeoTaskQuartet("a", "b", "c", "d")

        assertThat(item.first).isEqualTo("a")
        assertThat(item.second).isEqualTo("b")
        assertThat(item.third).isEqualTo("c")
        assertThat(item.fourth).isEqualTo("d")

        assertThat(item.toString()).isEqualTo(
                "NeoTaskQuartet(first=a, second=b, third=c, fourth=d)")
        assertThat(item.hashCode()).isEqualTo(2987074)
    }
}
