package com.example.kreditplusandroidassessment.domain.adapter.popular

import androidx.recyclerview.widget.RecyclerView
import com.example.kreditplusandroidassessment.databinding.MovieItemLayoutBinding
import com.example.kreditplusandroidassessment.domain.model.Popular
import com.example.kreditplusandroidassessment.util.dateConverter
import com.example.kreditplusandroidassessment.util.loadImage

class PopularViewHolder(
    val binding: MovieItemLayoutBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Popular) {
        with(binding) {
            ivImage.loadImage(item.backdropPath)
            tvName.text = item.title
            tvDate.text = String.format("Initial Date: %s", item.releaseDate.dateConverter())
        }
    }
}