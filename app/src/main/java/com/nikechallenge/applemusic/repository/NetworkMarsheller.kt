package com.nikechallenge.applemusic.repository

import com.nikechallenge.applemusic.model.MostPlayed

import com.nikechallenge.applemusic.network.model.FinalResult

internal fun FinalResult.toMostPlayedSongsList(): List<MostPlayed> {
    val res = mutableListOf<MostPlayed>()
    for (data in this.feed.results) {
        val item = MostPlayed(
            artistName = data.artistName,
            songName = data.name,
            imgUrl = data.artworkUrl100,
            genre = data.genres[0].name,
            artistDetail = data.artistUrl,
            releaseDate = data.releaseDate
        )
        res.add(item)
    }
    return res
}