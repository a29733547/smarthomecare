package com.czerny.smarthomecare.home
import com.czerny.smarthomecare.databinding.ItemRemindBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.czerny.smarthomecare.data.Remind

import java.util.concurrent.CancellationException

class HomeAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Remind, RecyclerView.ViewHolder>(DiffCallBack) {


    class OnClickListener(val clickListener: (remind: Remind) -> Unit) {
        fun onClick(remind: Remind) = clickListener(remind)
    }

    class HomeViewHolder(var binding: ItemRemindBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(remind: Remind, onClickListener: OnClickListener) {
            binding.viewModelHomeItemRemind = remind

            binding.root.setOnClickListener {
                onClickListener.onClick(remind)
                if (binding.layoutItemCheck.visibility == View.GONE) {
                    binding.layoutItemCheck.visibility = View.VISIBLE
                } else {
                    binding.layoutItemCheck.visibility = View.GONE
                }
            }


            binding.buttonCheck.setOnClickListener {
                binding.imageHomeItemYet.visibility = View.INVISIBLE
                binding.imageHomeItemOk.visibility = View.VISIBLE
            }
            binding.buttonCancel.setOnClickListener {
                binding.imageHomeItemYet.visibility = View.VISIBLE
                binding.imageHomeItemOk.visibility = View.INVISIBLE
            }




            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE -> HomeViewHolder(
                ItemRemindBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> throw CancellationException("Unknown viewType $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> holder.bind((getItem(position) as Remind), onClickListener)
        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Remind>() {
        override fun areItemsTheSame(oldItem: Remind, newItem: Remind): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Remind, newItem: Remind): Boolean {
            return oldItem == newItem
        }

        private const val ITEM_VIEW_TYPE = 0x00
        private const val ITEM_VIEW_TYPE_CHECK = 0x01
    }



}



