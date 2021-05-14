package com.czerny.smarthomecare.savedata.remind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.databinding.ItemRemindBinding
import com.czerny.smarthomecare.home.HomeViewHolder

class HomeAdapter (): ListAdapter<MockData, RemindViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemindViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RemindViewHolder(
            ItemRemindBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RemindViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}



class RemindViewHolder( var binding: ItemRemindBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MockData) {
        binding.itemRemindViewModel = item
        binding.executePendingBindings()
    }
}

class DiffCallBack : DiffUtil.ItemCallback<MockData>() {
    override fun areItemsTheSame(oldItem: MockData, newItem: MockData): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: MockData, newItem: MockData): Boolean {
        return oldItem == newItem
    }
}