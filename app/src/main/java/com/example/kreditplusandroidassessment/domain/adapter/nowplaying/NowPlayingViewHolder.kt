package com.example.kreditplusandroidassessment.domain.adapter.nowplaying

import androidx.recyclerview.widget.RecyclerView
import com.example.kreditplusandroidassessment.databinding.MovieItemLayoutBinding
import com.example.kreditplusandroidassessment.domain.model.NowPlaying
import com.example.kreditplusandroidassessment.util.dateConverter
import com.example.kreditplusandroidassessment.util.loadImage

class NowPlayingViewHolder(
    val binding: MovieItemLayoutBinding
): RecyclerView.ViewHolder(
    binding.root
) {
    fun bind(itemList: NowPlaying) {
        with(binding) {
            ivImage.loadImage(itemList.backdropPath)
            tvName.text = itemList.title
            tvDate.text = String.format("Initial Date: %s", itemList.releaseDate.dateConverter())
        }
    }
}