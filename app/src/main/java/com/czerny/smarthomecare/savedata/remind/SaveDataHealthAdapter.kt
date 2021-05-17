package com.czerny.smarthomecare.savedata.remind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czerny.smarthomecare.MockData

import com.czerny.smarthomecare.databinding.ItemSavedataHealthBinding

class SaveDataHealthAdapter (): ListAdapter<MockData, SaveDataHealthViewHoler>(healthDiffCallBack()) {
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
    fun bind(item: MockData) {
        binding.viewModelItemSaveDataHealth= item
        binding.executePendingBindings()
    }
}

class healthDiffCallBack : DiffUtil.ItemCallback<MockData>() {
    override fun areItemsTheSame(oldItem: MockData, newItem: MockData): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: MockData, newItem: MockData): Boolean {
        return oldItem == newItem
    }
}