package com.example.kreditplusandroidassessment.domain.adapter.upcoming

import androidx.recyclerview.widget.RecyclerView
import com.example.kreditplusandroidassessment.databinding.MovieItemLayoutBinding
import com.example.kreditplusandroidassessment.domain.model.Upcoming
import com.example.kreditplusandroidassessment.util.dateConverter
import com.example.kreditplusandroidassessment.util.loadImage

class UpcomingViewHolder(
    val binding: MovieItemLayoutBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Upcoming) {
        with(binding) {
            ivImage.loadImage(item.backdropPath)
            tvName.text = item.title
            tvDate.text = String.format("Initial Date: %s", item.releaseDate.dateConverter())
        }
    }
}