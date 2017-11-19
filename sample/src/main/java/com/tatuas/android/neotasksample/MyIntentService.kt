package com.tatuas.android.neotasksample

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.tatuas.android.neotask.NeoTask
import com.tatuas.android.neotask.thenCallableBlocking
import com.tatuas.android.neotasksample.Utils.log

class MyIntentService : IntentService("MyIntentService") {

    companion object {
        fun createService(context: Context) = Intent(context, MyIntentService::class.java)
    }

    override fun onHandleIntent(intent: Intent?) {

        NeoTask.callBlocking(SampleCallable.StringCallable())
                .thenCallableBlocking { SampleCallable.StringCallable2(it ?: "empty") }
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        log(it.result ?: "empty")
                    } else {
                        log(it.toString())
                    }
                }
    }
}
