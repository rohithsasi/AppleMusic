package com.nikechallenge.applemusic.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikechallenge.applemusic.AppleMusicApp
import com.nikechallenge.applemusic.LocalCache
import com.nikechallenge.applemusic.R
import com.nikechallenge.applemusic.model.MostPlayed

class MusicAdapter(
    context: Context
) : RecyclerView.Adapter<MusicAdapter.MusicDataViewHolder>() {

    private var items = mutableListOf<MostPlayed>()

    fun updateItems(results: List<MostPlayed>) {
        items = results as MutableList<MostPlayed>
        items.removeLast()
        // replace with Diff Util's
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicDataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_song, parent, false)
        return MusicDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicDataViewHolder, position: Int) {
        items.getOrNull(position)?.let { it ->
            holder.bind(it, position)
        }
        holder.itemView.setOnClickListener {
            //For this sample exercise purposes.
            LocalCache.songDetail = items[position]
            SongDetailActivity.launchActivity(it.context)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MusicDataViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private val artist: TextView = view.findViewById(R.id.artist)
        private val song: TextView = view.findViewById(R.id.song)
        private val icon: ImageView = view.findViewById(R.id.cover_img)

        /**
         * binds the data with ui elements.
         */
        fun bind(item: MostPlayed, pos: Int) {
            artist.text = item.artistName
            song.text = item.songName
            Glide.with(AppleMusicApp.APPLICATION)
                .load(item.imgUrl)
                .into(icon)

        }
    }
}