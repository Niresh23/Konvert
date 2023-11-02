package com.niresh23.konvert.ui.rate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.niresh23.konvert.databinding.ListItemRateBinding
import com.niresh23.konvert.model.Rate

class RateAdapter(private val list: List<Rate>, private val favoriteCallback: ((Rate) -> Unit)?): RecyclerView.Adapter<RateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rate = list[position]
        holder.bind(rate)
    }

    inner class ViewHolder(private val binding: ListItemRateBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(rate: Rate) {
            binding.code.text = rate.code
            binding.favoriteBtn.setOnClickListener {
                favoriteCallback?.invoke(rate)
            }
            binding.favoriteBtn.isSelected = rate.isFavorite
        }
    }
}