package com.czerny.smarthomecare.chatroom


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czerny.smarthomecare.data.Message
import com.czerny.smarthomecare.databinding.ItemChatroomMessageBinding


class ChatRoomAdapter : ListAdapter<Message, ChatRoomViewHolder>(DiffCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChatRoomViewHolder(
                ItemChatroomMessageBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ChatRoomViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}


class ChatRoomViewHolder( var binding: ItemChatroomMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Message) {
        binding.viewModelChatroomMessage = item
        binding.executePendingBindings()
    }
}

class DiffCallBack : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
}