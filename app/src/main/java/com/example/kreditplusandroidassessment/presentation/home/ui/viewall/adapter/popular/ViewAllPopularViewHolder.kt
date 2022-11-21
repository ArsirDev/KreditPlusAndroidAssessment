package com.example.kreditplusandroidassessment.presentation.home.ui.viewall.adapter.popular

import androidx.recyclerview.widget.RecyclerView
import com.example.kreditplusandroidassessment.databinding.ItemViewAllBinding
import com.example.kreditplusandroidassessment.domain.model.Popular
import com.example.kreditplusandroidassessment.util.loadImage

class ViewAllPopularViewHolder(
    val binding: ItemViewAllBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Popular) {
        binding.ivImage.loadImage(item.posterPath.toString())
        binding.tvTitle.text = item.title
    }
}