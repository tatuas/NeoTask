package com.tatuas.android.neotasksample

import android.content.Context
import android.content.Intent
import android.support.v4.app.JobIntentService
import android.widget.Toast
import com.tatuas.android.neotask.*

class MyJobIntentService : JobIntentService() {

    companion object {
        private const val JOB_ID = 4

        fun enqueueWork(context: Context, work: Intent = Intent()) {
            enqueueWork(context, MyJobIntentService::class.java, JOB_ID, work)
        }
    }

    override fun onHandleWork(intent: Intent) {
        MyUtils.log("JobIntentService Start: ${Thread.currentThread()}")

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
                .addOnBlockingCompleteListener {
                    MyUtils.log("listener: ${Thread.currentThread()}")
                    if (it.isSuccessful) {
                        MyUtils.log("result: ${it.result}")
                    } else {
                        MyUtils.log("exception: ${it.exception}")
                    }
                }

        MyUtils.log("JobIntentService Stop: ${Thread.currentThread()}")
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(this, "Job done", Toast.LENGTH_SHORT).show()
    }
}
