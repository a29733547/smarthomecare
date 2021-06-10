package com.czerny.smarthomecare.userfamily

import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.databinding.ItemRemindBinding

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.czerny.smarthomecare.data.UserInfo

import com.czerny.smarthomecare.databinding.ItemUserListBinding

import java.util.concurrent.CancellationException

class UserAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<UserInfo, RecyclerView.ViewHolder>(DiffCallBack) {


    class OnClickListener(val clickListener: (userInfo: UserInfo) -> Unit) {
        fun onClick(userInfo: UserInfo) = clickListener(userInfo)
    }

    class UserViewHolder(var binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userInfo: UserInfo, onClickListener: OnClickListener) {
            binding.userInfo = userInfo

            binding.root.setOnClickListener {
                onClickListener.onClick(userInfo)

            }



            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE -> UserViewHolder(
                ItemUserListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )


            else -> throw CancellationException("Unknown viewType $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> holder.bind((getItem(position) as UserInfo), onClickListener)

        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<UserInfo>() {
        override fun areItemsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
            return oldItem == newItem
        }

        private const val ITEM_VIEW_TYPE = 0x00

    }


}



