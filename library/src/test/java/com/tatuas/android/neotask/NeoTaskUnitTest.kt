package com.tatuas.android.neotask

import com.tatuas.android.neotask.util.assertEquals
import org.assertj.core.api.Assertions
import org.awaitility.Awaitility
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NeoTaskUnitTest {

    @Test
    fun checkAsync() {
        assertEquals("async:equals2", NeoTask.async { "async:equals2" })
    }

    @Test
    fun checkBlocking() {
        assertEquals("blocking:equals2", NeoTask.blocking { "blocking:equals2" })
    }

    @Test
    fun checkMain() {
        assertEquals("main:equals2", NeoTask.main { "main:equals2" })
    }

    @Test
    fun checkAwait() {
        assertEquals("await:equals2", { NeoTask.await(NeoTask.async { "await:equals2" }) })
    }

    @Test
    fun checkAwaitException() {
        Awaitility.await()
                .atMost(10L, TimeUnit.SECONDS)
                .until {
                    true.also {
                        try {
                            NeoTask.await(NeoTask.async { throw IllegalStateException() })
                        } catch (e: Exception) {
                            Assertions.assertThat(e::class.java).isEqualTo(IllegalStateException::class.java)
                        }
                    }
                }
    }

    @Test
    fun checkAwaitSequentialPair() {
        assertEquals("ab", {
            NeoTask.awaitSequential(
                    NeoTask.async { "a" },
                    { NeoTask.async { it + "b" } })
        })
    }

    @Test
    fun checkAwaitSequentialTriple() {
        assertEquals("abc", {
            NeoTask.awaitSequential(
                    NeoTask.async { "a" },
                    { NeoTask.async { it + "b" } },
                    { NeoTask.async { it + "c" } })
        })
    }

    @Test
    fun checkAwaitSequentialTripleException() {
        Awaitility.await()
                .atMost(10L, TimeUnit.SECONDS)
                .until {
                    true.also {
                        try {
                            NeoTask.awaitSequential(
                                    NeoTask.async { "a" },
                                    { NeoTask.async { throw IllegalStateException() } },
                                    { NeoTask.async { "hello" } })
                        } catch (e: Exception) {
                            Assertions.assertThat(e::class.java)
                                    .isEqualTo(IllegalStateException::class.java)
                        }
                    }
                }
    }

    @Test
    fun checkAwaitSequentialQuartet() {
        assertEquals("abcd", {
            NeoTask.awaitSequential(
                    NeoTask.async { "a" },
                    { NeoTask.async { it + "b" } },
                    { NeoTask.async { it + "c" } },
                    { NeoTask.async { it + "d" } })
        })
    }

    @Test
    fun checkAwaitSequentialQuintet() {
        assertEquals("abcde", {
            NeoTask.awaitSequential(
                    NeoTask.async { "a" },
                    { NeoTask.async { it + "b" } },
                    { NeoTask.async { it + "c" } },
                    { NeoTask.async { it + "d" } },
                    { NeoTask.async { it + "e" } })
        })
    }

    @Test
    fun checkWhenAllPair() {
        assertEquals(Pair("a", "b"),
                NeoTask.whenAll(
                        NeoTask.blocking { "a" },
                        NeoTask.blocking { "b" }))
    }

    @Test
    fun checkWhenAllTriple() {
        assertEquals(Triple("a", "b", "c"),
                NeoTask.whenAll(
                        NeoTask.blocking { "a" },
                        NeoTask.blocking { "b" },
                        NeoTask.blocking { "c" }))
    }

    @Test
    fun checkWhenAllQuartet() {
        assertEquals(NeoTaskQuartet("a", "b", "c", "d"),
                NeoTask.whenAll(
                        NeoTask.blocking { "a" },
                        NeoTask.blocking { "b" },
                        NeoTask.blocking { "c" },
                        NeoTask.blocking { "d" }))
    }

    @Test
    fun checkWhenAllQuintet() {
        assertEquals(NeoTaskQuintet("a", "b", "c", "d", "e"),
                NeoTask.whenAll(
                        NeoTask.blocking { "a" },
                        NeoTask.blocking { "b" },
                        NeoTask.blocking { "c" },
                        NeoTask.blocking { "d" },
                        NeoTask.blocking { "e" }))
    }
}
