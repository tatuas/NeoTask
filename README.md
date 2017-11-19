
[![Release](https://jitpack.io/v/tatuas/NeoTask.svg)](https://jitpack.io/#tatuas/NeoTask)

# NeoTask

A extension library that you can use Google Play Services Task API more sexy!

# Requirements

- Android Project written in Kotlin

# Usage

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

## Add it in your root build.gradle with: 

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
