
[![Release](https://jitpack.io/v/tatuas/NeoTask.svg?style=flat-square)](https://jitpack.io/#tatuas/NeoTask)

[![CircleCI](https://circleci.com/gh/tatuas/NeoTask/tree/master.svg?style=svg)](https://circleci.com/gh/tatuas/NeoTask/tree/master)

# NeoTask

A extension library that you can use Google Play Services Task API more sexy!

# Requirements

- Android Project written in Kotlin

# Recommend

- Android Project using Firebase Platform

# What is Task API

See the Firebase blog posts or Google I/O 2016

- https://firebase.googleblog.com/2016/09/become-a-firebase-taskmaster-part-1.html

- https://firebase.googleblog.com/2016/09/become-a-firebase-taskmaster-part-2.html

- https://firebase.googleblog.com/2016/09/become-a-firebase-taskmaster-part-3_29.html

- https://firebase.googleblog.com/2016/10/become-a-firebase-taskmaster-part-4.html

- https://www.youtube.com/watch?v=AJqakuas_6g&index=12&list=PLl-K7zZEsYLlAyGS6_paVoGJ9YKC7J3NN

# Usage

If you wants to learn more, see also the sample project this repo.

## Parallel Task Execution

```kotlin

NeoTask.parallel(
        NeoTask.callAsync(SampleCallable.LongCallable()),
        NeoTask.callAsync(SampleCallable.VoidCallable()),
        NeoTask.callAsync(SampleCallable.StringCallable()))
        .addOnSuccessListener(this, {
            // Things to do
        })
        .addOnFailureListener(this, {
            // Things to do
        })

```

## Series Task Execution

```kotlin

NeoTask.callAsync(SampleCallable.StringCallable())
        .thenCallable { SampleCallable.StringCallable2(",additional") }
        .addOnCompleteListener(this, {
            // Things to do
        })

```

# Installation

## Add it in your root build.gradle with

```

allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

```

## Add the dependency in app module

```

dependencies {
    implementation "com.tatuas.android:NeoTask:{$latest_version}"
}


```
