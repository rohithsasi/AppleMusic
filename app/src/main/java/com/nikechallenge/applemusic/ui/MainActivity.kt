package com.nikechallenge.applemusic.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikechallenge.applemusic.databinding.ActivityMainBinding
import com.nikechallenge.applemusic.ext.observe
import com.nikechallenge.applemusic.viewmodel.MusicViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MusicViewModel
    private lateinit var musicAdapter: MusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)
        viewModel = ViewModelProvider(this)[MusicViewModel::class.java]

        musicAdapter = MusicAdapter(this)
        binding.songList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = musicAdapter

            val dividerItemDecoration = DividerItemDecoration(
                this.context,
                LinearLayoutManager.VERTICAL
            )
            this.addItemDecoration(dividerItemDecoration)
        }
        listenToMusicDataUpdate()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getMostPlayed(50)
    }

    private fun listenToMusicDataUpdate() {
        viewModel.mostPlayed.observe(this, {
            musicAdapter.updateItems(it)
        }, {
            Toast.makeText(this, "Network Error : Failed to retrieve data", Toast.LENGTH_SHORT)
                .show()
            Log.d("TAG", "Failed to retrieve data", it)
        })
    }
}