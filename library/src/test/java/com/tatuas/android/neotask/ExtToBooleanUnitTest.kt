package com.tatuas.android.neotask

import com.tatuas.android.neotask.util.assertEquals
import com.tatuas.android.neotask.util.createVoidTask
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ExtToBooleanUnitTest {

    @Test
    fun checkToBoolean() {
        assertEquals(true, createVoidTask().toBoolean())
    }
}
