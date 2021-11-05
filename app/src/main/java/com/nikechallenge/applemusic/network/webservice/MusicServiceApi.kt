package com.nikechallenge.applemusic.network.webservice

import com.nikechallenge.applemusic.network.model.FinalResult
import retrofit2.http.GET
import retrofit2.http.Path

const val MUSIC = "api/v2/us/music/most-played/{limit}/songs.json"

interface MusicServiceApi {
    @GET(MUSIC)
    suspend fun fetchMusicData(
        @Path("limit") limit: Int,
    ): FinalResult
}