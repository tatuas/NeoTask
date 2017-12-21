package com.tatuas.android.neotask

import org.assertj.core.api.Assertions
import org.awaitility.Awaitility
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ExtListenerUnitTest {

    @Test
    fun checkAddOnBlockingCompleteListener() {
        Awaitility.waitAtMost(10, TimeUnit.SECONDS)
                .await()
                .until {
                    true.also {
                        NeoTask.blocking { "result" }.addOnBlockingCompleteListener {
                            Assertions.assertThat(it.result).isEqualTo("result")
                        }
                    }
                }
    }
}
