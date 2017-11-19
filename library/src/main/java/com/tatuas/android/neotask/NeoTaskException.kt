package com.tatuas.android.neotask

import com.google.android.gms.tasks.Task
import java.lang.Exception

class NeoTaskException(task: Task<*>) : Exception("Error Task: $task")
