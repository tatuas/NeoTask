package com.tatuas.android.neotasksample

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.google.android.gms.tasks.OnCompleteListener
import com.tatuas.android.neotask.NeoTask
import com.tatuas.android.neotask.NeoTaskExecutors
import com.tatuas.android.neotask.thenBlocking
import com.tatuas.android.neotask.thenCallableBlocking
import com.tatuas.android.neotasksample.Utils.log

class MyIntentService : IntentService("MyIntentService") {

    companion object {
        fun createService(context: Context) = Intent(context, MyIntentService::class.java)
    }

    override fun onHandleIntent(intent: Intent?) {
        NeoTask.blocking(SampleCallable.StringCallable())
                .thenCallableBlocking { SampleCallable.StringCallable2(it) }
                .thenBlocking { NeoTask.blocking { "$it and hello" } }
                .addOnCompleteListener(NeoTaskExecutors.CURRENT, OnCompleteListener {
                    if (it.isSuccessful) {
                        log(it.result)
                    } else {
                        log(it.toString())
                    }
                })
    }
}
