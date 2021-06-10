package com.czerny.smarthomecare.userfamily
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czerny.smarthomecare.data.FamilyInfo
import com.czerny.smarthomecare.data.User
import com.czerny.smarthomecare.databinding.ItemFamilyListBinding
import java.util.concurrent.CancellationException

class FamilyAdapter(private val onClickListener: OnClickListener ) :
    ListAdapter<FamilyInfo, RecyclerView.ViewHolder>(DiffCallBack) {


    class OnClickListener(val clickListener: (user: FamilyInfo) -> Unit) {
        fun onClick(familyInfo: FamilyInfo) = clickListener(familyInfo)
    }

    class FamilyViewHolder(var binding: ItemFamilyListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(familyInfo: FamilyInfo, onClickListener: OnClickListener) {
            binding.familyInfotest = familyInfo



            binding.layoutItemFamily.setOnClickListener {
                Navigation.createNavigateOnClickListener(
                    UserFamilyFragmentDirections.actionUserFamilyFragmentToChatRoomFragment(
                        familyInfo.familyName)).onClick(binding.layoutItemFamily)



            }


            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE -> FamilyViewHolder(
                ItemFamilyListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )


            else -> throw CancellationException("Unknown viewType $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FamilyViewHolder -> holder.bind((getItem(position) as FamilyInfo), onClickListener)

        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<FamilyInfo>() {
        override fun areItemsTheSame(oldItem: FamilyInfo, newItem: FamilyInfo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FamilyInfo, newItem: FamilyInfo): Boolean {
            return oldItem == newItem
        }

        private const val ITEM_VIEW_TYPE = 0x00

    }

}



