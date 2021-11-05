package com.nikechallenge.applemusic

import android.app.Application
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner

class AppleMusicApp : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        application = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        Log.d("AppleMusicApp", "App foreground")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        Log.d("AppleMusicApp", "App background")
    }

    companion object {
        private lateinit var application: Application
        val APPLICATION by lazy { application }
    }
}