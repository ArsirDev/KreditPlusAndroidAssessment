package com.example.kreditplusandroidassessment.presentation.home.ui.viewall.adapter.upcoming

import androidx.recyclerview.widget.RecyclerView
import com.example.kreditplusandroidassessment.databinding.ItemViewAllBinding
import com.example.kreditplusandroidassessment.domain.model.Upcoming
import com.example.kreditplusandroidassessment.util.loadImage

class ViewAllUpcomingViewHolder(val binding: ItemViewAllBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Upcoming) {
        binding.ivImage.loadImage(item.posterPath.toString())
        binding.tvTitle.text = item.title
    }
}