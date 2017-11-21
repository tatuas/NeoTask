package com.tatuas.android.neotask

class NeoTaskNullResultException : Exception(
        "The task succeeded, but the result data is null. NeoTask does not allow a null results.\n" +
                "if you want to execute void task, use toBoolean().")
