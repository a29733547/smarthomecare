package com.czerny.smarthomecare.savedata.remind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.data.Health

import com.czerny.smarthomecare.databinding.ItemSavedataHealthBinding

class SaveDataHealthAdapter (): ListAdapter<Health, SaveDataHealthViewHoler>(healthDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveDataHealthViewHoler {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SaveDataHealthViewHoler(
                ItemSavedataHealthBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SaveDataHealthViewHoler, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}
    //test git branch

class SaveDataHealthViewHoler( var binding: ItemSavedataHealthBinding) :
        RecyclerView.ViewHolder(binding.root) {
    fun bind(health: Health) {
        binding.viewModelItemSaveDataHealth= health
        binding.executePendingBindings()
    }
}

class healthDiffCallBack : DiffUtil.ItemCallback<Health>() {
    override fun areItemsTheSame(oldItem: Health, newItem: Health): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Health, newItem: Health): Boolean {
        return oldItem == newItem
    }
}