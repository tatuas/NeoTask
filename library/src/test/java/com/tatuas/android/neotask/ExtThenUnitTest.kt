package com.tatuas.android.neotask

import com.tatuas.android.neotask.util.assertEquals
import com.tatuas.android.neotask.util.createStringTask
import org.awaitility.Awaitility
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ExtThenUnitTest {

    @Test
    fun checkThen() {
        assertEquals(
                "result,additional",
                createStringTask().then { createStringTask(",additional") })
    }

    @Test
    fun checkThenAwait() {
        Awaitility.await()
                .atMost(10L, TimeUnit.SECONDS)
                .until {
                    true.also {
                        assertEquals("result,additional", createStringTask()
                                .thenAwait { createStringTask(",additional") })
                    }
                }
    }

    @Test
    fun checkThenAsync() {
        assertEquals(
                "result,additional",
                createStringTask().thenAsync { it + ",additional" })
    }

    @Test
    fun checkThenBlocking() {
        assertEquals(
                "result,additional",
                createStringTask().thenBlocking { it + ",additional" })
    }

    @Test
    fun checkThenMain() {
        assertEquals(
                "result,additional",
                createStringTask().thenMain { it + ",additional" })
    }
}
