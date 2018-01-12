[![Release](https://jitpack.io/v/tatuas/NeoTask.svg)](https://jitpack.io/#tatuas/NeoTask)
[![CircleCI](https://circleci.com/gh/tatuas/NeoTask/tree/master.svg?style=shield)](https://circleci.com/gh/tatuas/NeoTask/tree/master)
[![codecov](https://codecov.io/gh/tatuas/NeoTask/branch/master/graph/badge.svg)](https://codecov.io/gh/tatuas/NeoTask)

# NeoTask

NeoTask is an extension library that treats Tasks API smarter. For example, you can greatly simplify the code to perform asynchronous processing of the Tasks API in serial or parallel, or create Custom Task easily.

# Features

- You can use Tasks API greatly smart, especially parallel or series execution.

- Tasks API allows nullable result, but NeoTask does not allow nullable result, just like RxJava2. And you can easily convert from nullable result task to non-null result task using this library.

- NeoTask is a lightweight library, so you can easily try out, and install.

# Requirements

- Android SDK API Level 14 or more

- Android Project written in Kotlin

# Recommended environment

- Android Project using Firebase and Kotlin

# About Tasks API

Recommend to see the Firebase blog posts or Google I/O 2016 YouTube.

- https://firebase.googleblog.com/2016/09/become-a-firebase-taskmaster-part-1.html

- https://firebase.googleblog.com/2016/09/become-a-firebase-taskmaster-part-2.html

- https://firebase.googleblog.com/2016/09/become-a-firebase-taskmaster-part-3_29.html

- https://firebase.googleblog.com/2016/10/become-a-firebase-taskmaster-part-4.html

- https://www.youtube.com/watch?v=AJqakuas_6g

Tasks API Guides from Google.

- https://developers.google.com/android/guides/tasks

# Usage

## Series Task Execution

### Together with Firebase SDK

```kotlin

FirebaseAuth.getInstance().signInAnonymously()
  .then { it.user.getIdToken(false) }
  .addOnCompleteListener(this, {
    if (it.isSuccessful) {
      val result = it.result
      // Do something
    }
    else {
      val exception = it.exception
      // Do something
    }
  })
  
```

### Together with custom task

```kotlin

NeoTask.async { "result" }
  .thenAsync {
    listOf(it, "result2").joinToString(separator = ",")
  }
  .addOnCompleteListener(this, {
    if (it.isSuccessful) {
      val result = it.result
      // Do something
    }
    else {
      val exception = it.exception
      // Do something
    }
  })

```

## Parallel Task Execution

### Together with Firebase SDK

```kotlin

NeoTask.parallel(
    FirebaseFirestore.getInstance().collection("restaurants").add(restaurant1),
    FirebaseFirestore.getInstance().collection("users").add(user),
    FirebaseFirestore.getInstance().collection("settings").add(setting)
).addOnSuccessListener(this, {
    val result = "${it.first}, ${it.second}, ${it.third}"
    // Do something
  })
  .addOnFailureListener(this, {
    val exception = it
    // Do something
  })

```

### Together with custom task

```kotlin

NeoTask.parallel(
    NeoTask.async { "result1" },
    NeoTask.async { "result2" },
    NeoTask.async { "result3" },
    NeoTask.async { "result4" },
    NeoTask.async { "result5" })
  .addOnSuccessListener(this, {
    val result = "${it.first}, ${it.second}, ${it.third}, ${it.fourth}, ${it.five}"
    // Do something
  })
  .addOnFailureListener(this, {
    val exception = it
    // Do something
  })

```

## Await task execution

### Single await task execution

```kotlin

val idToken = NeoTask.await(firebaseUser.getIdToken(false), AwaitTimeout.seconds(30))

```

### Sequential await task execution

```kotlin

val logging = NeoTask.awaitSequential(
     NeoTask.await(firebaseUser.getIdToken(false), AwaitTimeout.seconds(30)),
     NeoTask.async { "token: " + it.token  })

```

If you wants to learn more, see also the sample project this repo.

# Installation

## Add repositories in your root build.gradle

```
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

## Add the dependency in app module

```
dependencies {
    implementation("com.github.tatuas:NeoTask:$latest_version") {
        transitive = true
    }
}
```

License
-------

    Copyright (C) 2017 Tatsuya Sawai

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
