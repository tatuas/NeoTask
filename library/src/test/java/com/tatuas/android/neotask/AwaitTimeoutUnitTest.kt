package com.tatuas.android.neotask

import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class AwaitTimeoutUnitTest {

    @Test
    fun checkConstructor() {
        val base = AwaitTimeout(3, TimeUnit.SECONDS)
        Assertions.assertThat(base.time).isEqualTo(3)
        Assertions.assertThat(base.unit).isEqualTo(TimeUnit.SECONDS)
    }

    @Test
    fun checkStaticMethods() {
        Assertions.assertThat(AwaitTimeout.nanos(3))
                .isEqualTo(AwaitTimeout(3, TimeUnit.NANOSECONDS))

        Assertions.assertThat(AwaitTimeout.micros(3))
                .isEqualTo(AwaitTimeout(3, TimeUnit.MICROSECONDS))

        Assertions.assertThat(AwaitTimeout.millis(3))
                .isEqualTo(AwaitTimeout(3, TimeUnit.MILLISECONDS))

        Assertions.assertThat(AwaitTimeout.seconds(3))
                .isEqualTo(AwaitTimeout(3, TimeUnit.SECONDS))

        Assertions.assertThat(AwaitTimeout.minutes(3))
                .isEqualTo(AwaitTimeout(3, TimeUnit.MINUTES))

        Assertions.assertThat(AwaitTimeout.hours(3))
                .isEqualTo(AwaitTimeout(3, TimeUnit.HOURS))

        Assertions.assertThat(AwaitTimeout.days(3))
                .isEqualTo(AwaitTimeout(3, TimeUnit.DAYS))
    }

    @Test
    fun checkDefault() {
        val default = AwaitTimeout.DEFAULT
        Assertions.assertThat(default.time).isEqualTo(3)
        Assertions.assertThat(default.unit).isEqualTo(TimeUnit.MINUTES)
    }
}
