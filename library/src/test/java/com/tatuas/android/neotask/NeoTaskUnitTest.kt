package com.tatuas.android.neotask

import com.tatuas.android.neotask.util.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

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
    fun checkParallelPair() {
        assertEquals(Pair("a", "b"),
                NeoTask.parallel(
                        NeoTask.blocking { "a" },
                        NeoTask.blocking { "b" }))
    }

    @Test
    fun checkParallelTriple() {
        assertEquals(Triple("a", "b", "c"),
                NeoTask.parallel(
                        NeoTask.blocking { "a" },
                        NeoTask.blocking { "b" },
                        NeoTask.blocking { "c" }))
    }

    @Test
    fun checkParallelQuartet() {
        assertEquals(NeoTaskQuartet("a", "b", "c", "d"),
                NeoTask.parallel(
                        NeoTask.blocking { "a" },
                        NeoTask.blocking { "b" },
                        NeoTask.blocking { "c" },
                        NeoTask.blocking { "d" }))
    }

    @Test
    fun checkParallelQuintet() {
        assertEquals(NeoTaskQuintet("a", "b", "c", "d", "e"),
                NeoTask.parallel(
                        NeoTask.blocking { "a" },
                        NeoTask.blocking { "b" },
                        NeoTask.blocking { "c" },
                        NeoTask.blocking { "d" },
                        NeoTask.blocking { "e" }))
    }
}
