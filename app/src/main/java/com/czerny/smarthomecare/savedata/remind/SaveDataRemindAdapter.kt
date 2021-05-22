package com.czerny.smarthomecare.savedata.remind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.databinding.ItemRemindBinding
import com.czerny.smarthomecare.databinding.ItemSavedataHealthBinding
import com.czerny.smarthomecare.databinding.ItemSavedataRemindBinding

class SaveDataRemindAdapter() : ListAdapter<Remind, SaveDataRemindViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveDataRemindViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SaveDataRemindViewHolder(
            ItemSavedataRemindBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SaveDataRemindViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

class SaveDataRemindViewHolder(var binding: ItemSavedataRemindBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(remind: Remind) {
        binding.viewModelItemSaveDataRemind = remind
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