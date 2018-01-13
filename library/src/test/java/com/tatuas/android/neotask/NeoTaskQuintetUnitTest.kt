package com.tatuas.android.neotask

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NeoTaskQuintetUnitTest {

    @Test
    fun check() {
        val item = NeoTaskQuintet("a", "b", "c", "d", "e")

        assertThat(item.first).isEqualTo("a")
        assertThat(item.second).isEqualTo("b")
        assertThat(item.third).isEqualTo("c")
        assertThat(item.fourth).isEqualTo("d")
        assertThat(item.five).isEqualTo("e")

        assertThat(item.toString()).isEqualTo("NeoTaskQuintet(first=a, second=b, third=c, fourth=d, five=e)")
        assertThat(item.hashCode()).isEqualTo(92599395)
    }
}
