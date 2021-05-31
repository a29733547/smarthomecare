package com.czerny.smarthomecare.chatroom


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czerny.smarthomecare.data.Message
import com.czerny.smarthomecare.databinding.ItemChatroomMessageBinding
import com.czerny.smarthomecare.databinding.ItemChatroomMessageFamilyBinding
import kotlin.coroutines.cancellation.CancellationException


class ChatRoomAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(DiffCallBack) {

    class ChatRoomFamilyViewHolder(private var binding: ItemChatroomMessageFamilyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meassage: Message) {
            binding.familyMessage = meassage
            binding.executePendingBindings()
        }
    }

    class ChatRoomViewHolder(private var binding: ItemChatroomMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meassage: Message) {
            binding.viewModelChatroomMessage = meassage
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_FAMILY -> ChatRoomFamilyViewHolder(
                ItemChatroomMessageFamilyBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            ITEM_VIEW_TYPE_MY -> ChatRoomViewHolder(
                ItemChatroomMessageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> throw CancellationException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ChatRoomFamilyViewHolder -> {
                holder.bind((getItem(position) as Message))
            }
            is ChatRoomViewHolder -> {
                holder.bind((getItem(position) as Message))
            }
        }
    }


    companion object DiffCallBack : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }

        private const val ITEM_VIEW_TYPE_FAMILY = 0x00
        private const val ITEM_VIEW_TYPE_MY = 0x01

    }

}