package com.tatuas.android.neotasksample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.tatuas.android.neotask.*
import kotlinx.android.synthetic.main.activity_my.*

class MyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        parallelButton.setOnClickListener {
            NeoTask.parallel(
                    NeoTask.async {
                        MyUtils.log("start: {${Thread.currentThread()}")
                        MyUtils.sleep()
                        MyUtils.log("stop: {${Thread.currentThread()}")
                        "string1"
                    },
                    MyUtils.createVoidTask().toBoolean(),
                    NeoTask.async {
                        MyUtils.log("start: {${Thread.currentThread()}")
                        MyUtils.sleep()
                        MyUtils.log("stop: {${Thread.currentThread()}")
                        "string2"
                    },
                    MyUtils.createVoidTask().toBoolean(),
                    NeoTask.async {
                        MyUtils.log("start: {${Thread.currentThread()}")
                        MyUtils.sleep()
                        MyUtils.log("stop: {${Thread.currentThread()}")
                        "string3"
                    })
                    .addOnSuccessListener(this, {
                        toast(listOf(it.first, it.second, it.third, it.fourth, it.five)
                                .joinToString(separator = ",", prefix = "[", postfix = "]"))
                    })
                    .addOnFailureListener(this, {
                        toast(it.toString())
                    })
        }

        thenButton.setOnClickListener {
            NeoTask.main { progress.visibility = View.VISIBLE }
                    .then {
                        MyUtils.createVoidTask().toBoolean()
                    }
                    .then {
                        NeoTask.blocking { "$it, blocking" }
                    }
                    .then {
                        NeoTask.async { "$it, async" }
                    }
                    .thenAsync {
                        MyUtils.log("start: ${Thread.currentThread()}")
                        MyUtils.sleep()
                        MyUtils.log("stop: ${Thread.currentThread()}")
                        "$it, string1"
                    }
                    .thenAsync {
                        MyUtils.log("start: ${Thread.currentThread()}")
                        MyUtils.sleep()
                        MyUtils.log("stop: ${Thread.currentThread()}")
                        "$it, string2"
                    }
                    .thenAsync {
                        MyUtils.log("start: ${Thread.currentThread()}")
                        MyUtils.sleep()
                        MyUtils.log("stop: ${Thread.currentThread()}")
                        "$it, string3"
                    }
                    .thenMain {
                        MyUtils.log("main")
                        it.also { progress.visibility = View.GONE }
                    }
                    .addOnCompleteListener(this, {
                        if (it.isSuccessful) {
                            toast(it.result.toString())
                        } else {
                            toast(it.exception.toString())
                        }
                    })
        }

        intentServiceButton.setOnClickListener {
            startService(MyIntentService.createService(this))
        }
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
