package com.nikechallenge.applemusic.ext

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineExceptionHandler

@MainThread
fun <T> MutableLiveData<Result<T>>.setSuccess(value: T) {
    this.value = Result.success(value)
}

@MainThread
fun <T> MutableLiveData<Result<T>>.setFailure(exception: Throwable) {
    value = Result.failure(exception)
}

fun <T> MutableLiveData<Result<T>>.postSuccess(data: T) = postValue(Result.success(data))

fun <T> MutableLiveData<Result<T>>.postFailure(exception: Throwable) = postValue(Result.failure(exception))

fun <T> MutableLiveData<Result<T>>.getExceptionHandler(): CoroutineExceptionHandler =
    CoroutineExceptionHandler { _, e -> postFailure(e) }
