package com.nikechallenge.applemusic.network.api

import com.nikechallenge.applemusic.network.AppleMusicRestClient
import com.nikechallenge.applemusic.network.model.FinalResult
import com.nikechallenge.applemusic.network.webservice.MusicServiceApi

/**
 * Api layer responsible for the network request
 */
internal interface MusicApi {

    suspend fun getMostPlayed(limit: Int): FinalResult

    companion object {
        fun get(): MusicApi {
            return MusicApiImpl
        }
    }
}

/**
 * Rest client
 */
private val MUSIC_SERVICE_API: MusicServiceApi by lazy {
    AppleMusicRestClient.get().musicServiceApi
}

internal object MusicApiImpl : MusicApi {

    override suspend fun getMostPlayed(limit: Int): FinalResult {
        return MUSIC_SERVICE_API.fetchMusicData(limit)
    }

}