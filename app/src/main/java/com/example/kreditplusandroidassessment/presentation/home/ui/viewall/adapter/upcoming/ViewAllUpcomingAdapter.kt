package com.example.kreditplusandroidassessment.presentation.home.ui.viewall.adapter.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kreditplusandroidassessment.databinding.ItemViewAllBinding
import com.example.kreditplusandroidassessment.domain.model.Upcoming

class ViewAllUpcomingAdapter: RecyclerView.Adapter<ViewAllUpcomingViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Upcoming>() {
        override fun areItemsTheSame(oldItem: Upcoming, newItem: Upcoming): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Upcoming, newItem: Upcoming): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAllUpcomingViewHolder {
        return ViewAllUpcomingViewHolder(ItemViewAllBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holderUpcoming: ViewAllUpcomingViewHolder, position: Int) {
        holderUpcoming.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    companion object {
        fun instance() = ViewAllUpcomingAdapter()
    }
}