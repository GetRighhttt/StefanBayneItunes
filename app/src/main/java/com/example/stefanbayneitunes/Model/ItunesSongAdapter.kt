package com.example.stefanbayneitunes.Model

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stefanbayneitunes.DataClass.AllDataOfTheSongs
import com.example.stefanbayneitunes.R
import com.squareup.picasso.Picasso


class ItunesSongAdapter(private val list: List<AllDataOfTheSongs>): RecyclerView.Adapter<ItunesSongAdapter.SongViewHolder>() {

    // Inner class to begin the adaptation of our data source to our UI
    inner class  SongViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

        // method to bind all the views in the layout to the recyclerView
        fun onBind(iTunesSong: AllDataOfTheSongs){
            // Initializing all the views so we can use them
            val tvArtist: TextView = itemView.findViewById(R.id.name_Of_Artist)
            val tvCollection: TextView = itemView.findViewById(R.id.genre_type)
            val tvSong : TextView =  itemView.findViewById(R.id.name_Of_Song)
            val tvSongPrice: TextView = itemView.findViewById(R.id.price_Of_Song)
            val ivUserThumbnail: ImageView = itemView.findViewById(R.id.song_image)

            // Assigning the views to the itunes information
            tvArtist.text = iTunesSong.artistName
            tvCollection.text = iTunesSong.collectionName
            tvSong.text = iTunesSong.trackName

            // assigning the price of each song
            tvSongPrice.text = "$" + iTunesSong.trackPrice.toString()
            tvSongPrice.text = tvSongPrice.text.replace("^[$][-].*$".toRegex(), "***FREE***")

            // Loading the image into the recyclerView with Picasso
            Picasso.get()
                .load(iTunesSong.artworkUrl60)
                .placeholder(R.drawable.ic_baseline_dashboard_24)
                .fit()
                .into(ivUserThumbnail)

            // Making the itunes song able to be played with the previewUrl
            itemView.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.setDataAndType(Uri.parse(iTunesSong.previewUrl), "audio/*")
                itemView.context.startActivity(intent)
            }
        }
    }

    // Creates the ViewHolder and inflates the layour
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.image_for_itunes_song, parent, false)

        return SongViewHolder(listItem)
    }

    // Binds the viewholder to the position
    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    // returns the item count in the recyclerView
    override fun getItemCount(): Int {
        return list.size
    }
}