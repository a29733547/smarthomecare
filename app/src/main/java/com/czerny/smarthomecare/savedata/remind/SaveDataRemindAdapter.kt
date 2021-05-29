package com.czerny.smarthomecare.savedata.remind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.databinding.ItemRemindBinding
import com.czerny.smarthomecare.databinding.ItemSavedataHealthBinding
import com.czerny.smarthomecare.databinding.ItemSavedataRemindBinding

//20210529 branch test

class SaveDataRemindAdapter(val viewModel: SaveDataRemindViewModel, private val onClickListener:OnClickListener) :
    ListAdapter<Remind, RecyclerView.ViewHolder>(DiffCallBack) {

    class OnClickListener(val clickListener: (remind: Remind) -> Unit) {
        fun onClick(remind: Remind) = clickListener(remind)
    }

    class SaveDataRemindViewHolder(private var binding: ItemSavedataRemindBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(remind: Remind, onClickListener: OnClickListener, viewModel: SaveDataRemindViewModel) {



            binding.root.setOnClickListener { onClickListener.onClick(remind) }
            binding.viewModelItemSaveDataRemind = remind
            binding.executePendingBindings()


            binding.imageSavedataRemindRemove.setOnClickListener{
                viewModel.deleteRemind(remind)
            }

        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Remind>() {
        override fun areItemsTheSame(oldItem: Remind, newItem: Remind): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Remind, newItem: Remind): Boolean {
            return oldItem.id == newItem.id
        }
        private const val ITEM_VIEW_TYPE_ARTICLE = 0x00
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE_ARTICLE -> SaveDataRemindAdapter.SaveDataRemindViewHolder(
                ItemSavedataRemindBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SaveDataRemindViewHolder
            -> {
                holder.bind((getItem(position)as Remind), onClickListener, viewModel)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return ITEM_VIEW_TYPE_ARTICLE
    }

}