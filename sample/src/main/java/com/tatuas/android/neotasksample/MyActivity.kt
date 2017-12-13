package com.tatuas.android.neotasksample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.tatuas.android.neotask.NeoTask
import com.tatuas.android.neotask.then
import com.tatuas.android.neotask.thenCallable
import com.tatuas.android.neotask.toBoolean
import kotlinx.android.synthetic.main.activity_my.*

class MyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        parallelButton.setOnClickListener {
            NeoTask.parallel(
                    NeoTask.async { "string" },
                    NeoTask.async(SampleCallable.VoidCallable()).toBoolean(),
                    NeoTask.async(SampleCallable.StringCallable()),
                    NeoTask.async(SampleCallable.StringCallable()),
                    NeoTask.async(SampleCallable.StringCallable()))
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
                    .thenCallable { SampleCallable.StringCallable() }
                    .then { NeoTask.async { "task1Value: $it" } }
                    .thenCallable { SampleCallable.StringCallable2("$it, task2Value: ") }
                    .then { NeoTask.main { it.also { progress.visibility = View.GONE } } }
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
