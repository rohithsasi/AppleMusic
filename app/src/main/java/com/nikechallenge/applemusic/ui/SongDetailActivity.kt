package com.nikechallenge.applemusic.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.nikechallenge.applemusic.AppleMusicApp
import com.nikechallenge.applemusic.LocalCache
import com.nikechallenge.applemusic.databinding.ActivitySongDetailBinding

class SongDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongDetailBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)
        updateView()
    }

    private fun updateView() {
        binding.apply {
            LocalCache.songDetail?.also {
                artist.text = it.artistName
                song.text = it.songName
                genre.text = it.genre
                date.text = it.releaseDate

                Glide.with(AppleMusicApp.APPLICATION)
                    .load(it.imgUrl)
                    .into(img)

                img.setOnClickListener {
                    startActivity(Intent(this@SongDetailActivity, WebViewActivity::class.java))
                }
            }
        }
    }

    companion object {
        fun launchActivity(context: Context) {
            context.startActivity(Intent(context, SongDetailActivity::class.java))
        }
    }
}