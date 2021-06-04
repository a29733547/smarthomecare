package com.czerny.smarthomecare.home

import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.databinding.ItemRemindBinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czerny.smarthomecare.data.Remind

class HomeAdapter : ListAdapter<Remind, HomeViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HomeViewHolder(
                ItemRemindBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

class HomeViewHolder( var binding:ItemRemindBinding) :
        RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Remind) {
        binding.viewModelHomeItemRemind = item
        binding.executePendingBindings()
    }
}

class DiffCallBack : DiffUtil.ItemCallback<Remind>() {
    override fun areItemsTheSame(oldItem: Remind, newItem: Remind): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Remind, newItem: Remind): Boolean {
        return oldItem == newItem
    }
}