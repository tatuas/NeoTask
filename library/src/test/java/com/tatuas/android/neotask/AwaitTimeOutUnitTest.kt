package com.tatuas.android.neotask

import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class AwaitTimeOutUnitTest {

    @Test
    fun checkConstructor() {
        val base = AwaitTimeOut(3, TimeUnit.SECONDS)
        Assertions.assertThat(base.time).isEqualTo(3)
        Assertions.assertThat(base.unit).isEqualTo(TimeUnit.SECONDS)
    }

    @Test
    fun checkStaticMethods() {
        Assertions.assertThat(AwaitTimeOut.nanos(3))
                .isEqualTo(AwaitTimeOut(3, TimeUnit.NANOSECONDS))

        Assertions.assertThat(AwaitTimeOut.micros(3))
                .isEqualTo(AwaitTimeOut(3, TimeUnit.MICROSECONDS))

        Assertions.assertThat(AwaitTimeOut.millis(3))
                .isEqualTo(AwaitTimeOut(3, TimeUnit.MILLISECONDS))

        Assertions.assertThat(AwaitTimeOut.seconds(3))
                .isEqualTo(AwaitTimeOut(3, TimeUnit.SECONDS))

        Assertions.assertThat(AwaitTimeOut.minutes(3))
                .isEqualTo(AwaitTimeOut(3, TimeUnit.MINUTES))

        Assertions.assertThat(AwaitTimeOut.hours(3))
                .isEqualTo(AwaitTimeOut(3, TimeUnit.HOURS))

        Assertions.assertThat(AwaitTimeOut.days(3))
                .isEqualTo(AwaitTimeOut(3, TimeUnit.DAYS))
    }

    @Test
    fun checkDefault() {
        val default = AwaitTimeOut.DEFAULT
        Assertions.assertThat(default.time).isEqualTo(3)
        Assertions.assertThat(default.unit).isEqualTo(TimeUnit.MINUTES)
    }
}
