package com.nikechallenge.applemusic.repository

import com.nikechallenge.applemusic.model.MostPlayed
import com.nikechallenge.applemusic.network.api.MusicApi

/**
 * Repository that fetches network data and emits results processed for ui to the view model
 */
interface MusicDataRepository {
    suspend fun getMostPlayed(
        limit: Int,
    ): List<MostPlayed>

    companion object {
        fun get(): MusicDataRepository {
            return MusicDataRepositoryImpl
        }
    }
}

internal object MusicDataRepositoryImpl : MusicDataRepository {
    private var music: MusicApi = MusicApi.get()

    override suspend fun getMostPlayed(
        limit: Int
    ): List<MostPlayed> {
        return music.getMostPlayed(limit).toMostPlayedSongsList()
    }
}