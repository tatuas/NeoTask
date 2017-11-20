package com.tatuas.android.neotask

import com.google.android.gms.tasks.Task

object NeoTaskUtils {

    @Throws(NeoTaskNullResultException::class)
    fun assertResultNotNull(vararg tasks: Task<*>) {
        tasks.filter { it.result == null }
                .indexOfFirst {
                    throw NeoTaskNullResultException()
                }
    }
}
