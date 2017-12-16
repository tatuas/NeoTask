package com.tatuas.android.neotasksample

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.google.android.gms.tasks.OnCompleteListener
import com.tatuas.android.neotask.*

class MyIntentService : IntentService("MyIntentService") {

    companion object {
        fun createService(context: Context) = Intent(context, MyIntentService::class.java)
    }

    override fun onHandleIntent(intent: Intent?) {

        MyUtils.log("IntentService Start: ${Thread.currentThread()}")

        NeoTask.blocking { MyUtils.sleep() }
                .thenAwait { MyUtils.createVoidTask().toBoolean() }
                .thenBlocking {
                    MyUtils.log("start:1 ${Thread.currentThread()}")
                    MyUtils.sleep()
                    MyUtils.log("stop:1 ${Thread.currentThread()}")
                    "$it, string2"
                }
                .thenBlocking {
                    MyUtils.log("start:2 ${Thread.currentThread()}")
                    MyUtils.sleep()
                    MyUtils.log("stop:2 ${Thread.currentThread()}")
                    "$it ,string3"
                }
                .addOnCompleteListener(NeoTaskExecutors.CURRENT, OnCompleteListener {
                    if (it.isSuccessful) {
                        MyUtils.log("result: ${it.result}")
                    } else {
                        MyUtils.log("exception: ${it.exception}")
                    }
                })

        MyUtils.log("IntentService Stop: ${Thread.currentThread()}")
    }
}
