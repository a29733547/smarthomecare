package com.czerny.smarthomecare.chatroom


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czerny.smarthomecare.data.ChatRoom

import com.czerny.smarthomecare.databinding.ItemChatroomMessageBinding
import com.czerny.smarthomecare.databinding.ItemChatroomMessageFamilyBinding
import com.czerny.smarthomecare.login.UserManager
import kotlin.coroutines.cancellation.CancellationException


class ChatRoomAdapter : ListAdapter<ChatRoom, RecyclerView.ViewHolder>(DiffCallBack) {

    class ChatRoomFamilyViewHolder(private var binding: ItemChatroomMessageFamilyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatRoom: ChatRoom) {
            binding.familyMessage = chatRoom
            binding.executePendingBindings()
        }
    }

    class ChatRoomViewHolder(private var binding: ItemChatroomMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatRoom: ChatRoom) {
            binding.viewModelChatroomMessage = chatRoom
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_MY -> ChatRoomViewHolder(
                ItemChatroomMessageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            ITEM_VIEW_TYPE_FAMILY -> ChatRoomFamilyViewHolder(
                ItemChatroomMessageFamilyBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> throw CancellationException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ChatRoomFamilyViewHolder -> {
                holder.bind((getItem(position) as ChatRoom))
            }
            is ChatRoomViewHolder -> {
                holder.bind((getItem(position) as ChatRoom))
            }
        }
    }


    companion object DiffCallBack : DiffUtil.ItemCallback<ChatRoom>() {
        override fun areItemsTheSame(oldItem: ChatRoom, newItem: ChatRoom): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ChatRoom, newItem: ChatRoom): Boolean {
            return oldItem == newItem
        }

        private const val ITEM_VIEW_TYPE_FAMILY = 0x01
        private const val ITEM_VIEW_TYPE_MY = 0x00

    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position).senderEmail){

            UserManager.user.userId -> ITEM_VIEW_TYPE_MY

            else -> ITEM_VIEW_TYPE_FAMILY

        }

    }
}