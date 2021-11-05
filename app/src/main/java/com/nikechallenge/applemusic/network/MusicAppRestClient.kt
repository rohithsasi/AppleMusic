package com.nikechallenge.applemusic.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nikechallenge.applemusic.network.webservice.MusicServiceApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Rest client, interceptors and adapters
 */
internal interface AppleMusicRestClient {
    val musicServiceApi: MusicServiceApi

    companion object {
        fun get(): AppleMusicRestClient {
            return AppleMusicRestClientImpl
        }
    }
}

private object AppleMusicRestClientImpl : AppleMusicRestClient {

    override val musicServiceApi: MusicServiceApi
        get() {
            return getMusicApiBuilder()
        }

    private var music: MusicServiceApi? = null

    private fun getMusicApiBuilder(): MusicServiceApi =
        music ?: appleMusicApiBuilder(MusicServiceApi::class.java).apply { music = this }


    private fun <T> appleMusicApiBuilder(
        clz: Class<T>,
        baseUrl: String = "https://rss.applemarketingtools.com/"
    ): T {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
            .create(clz)
    }

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()

    }

    private val okHttpClient: OkHttpClient
        get() {
            return getHttpClient()
        }
}
