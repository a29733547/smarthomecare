package com.czerny.smarthomecare.savedata.remind

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czerny.smarthomecare.data.Health

import com.czerny.smarthomecare.databinding.ItemSavedataHealthBinding

class SaveDataHealthAdapter(val viewModel: SaveDataHealthViewModel, private val onClickListener:OnClickListener) :
    ListAdapter<Health, RecyclerView.ViewHolder>(DiffCallBack) {

    class OnClickListener(val clickListener: (health: Health) -> Unit) {
        fun onClick(health: Health) = clickListener(health)
    }

    class SaveDataHealthViewHolder(private var binding: ItemSavedataHealthBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(health: Health, onClickListener: OnClickListener, viewModel: SaveDataHealthViewModel) {



            binding.root.setOnClickListener { onClickListener.onClick(health) }
            binding.viewModelItemSaveDataHealth = health
            binding.executePendingBindings()


            binding.layoutItemHealthCancel.setOnClickListener{
                viewModel.deleteHealth(health, viewModel.getFamily)
            }

        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Health>() {
        override fun areItemsTheSame(oldItem: Health, newItem: Health): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Health, newItem: Health): Boolean {
            return oldItem.id == newItem.id
        }
        private const val ITEM_VIEW_TYPE_ARTICLE = 0x00
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE_ARTICLE -> SaveDataHealthViewHolder(
                ItemSavedataHealthBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
//        val layoutInflater = LayoutInflater.from(parent.context)
//        return (viewTypeSaveDataHealthViewHolder(
//            ItemSavedataHealthBinding.inflate(layoutInflater, parent, false)
//        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SaveDataHealthViewHolder -> {
                holder.bind((getItem(position)as Health), onClickListener, viewModel)
            }
        }

    }



    override fun getItemViewType(position: Int): Int {
        return ITEM_VIEW_TYPE_ARTICLE
    }

}






