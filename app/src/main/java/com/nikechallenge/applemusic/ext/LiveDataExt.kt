package com.nikechallenge.applemusic.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<Result<T>>.observe(
    owner: LifecycleOwner,
    crossinline onSuccess: (T) -> Unit,
    crossinline onFailure: (Throwable) -> Unit
) {
    observe(owner, Observer {
        it.onSuccess(onSuccess)
        it.onFailure(onFailure)
    })
}