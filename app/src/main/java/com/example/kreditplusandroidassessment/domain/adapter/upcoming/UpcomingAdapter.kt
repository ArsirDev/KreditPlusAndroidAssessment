package com.example.kreditplusandroidassessment.domain.adapter.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kreditplusandroidassessment.databinding.MovieItemLayoutBinding
import com.example.kreditplusandroidassessment.domain.model.Upcoming
import com.example.kreditplusandroidassessment.util.setOnClickListenerWithDebounce

class UpcomingAdapter: RecyclerView.Adapter<UpcomingViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Upcoming>(){
        override fun areItemsTheSame(
            oldItem: Upcoming,
            newItem: Upcoming
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Upcoming,
            newItem: Upcoming
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        return UpcomingViewHolder(MovieItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.apply {
            bind(differ.currentList[position].also { item ->
                itemView.setOnClickListenerWithDebounce {
                    onItemClickListener?.let { id ->
                        id(item.id)
                    }
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        fun instance() = UpcomingAdapter()
    }
}