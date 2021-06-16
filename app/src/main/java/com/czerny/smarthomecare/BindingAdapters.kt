package com.czerny.smarthomecare


import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.home.HomeAdapter
import com.czerny.smarthomecare.savedata.remind.SaveDataHealthAdapter
import com.czerny.smarthomecare.savedata.remind.SaveDataRemindAdapter


@BindingAdapter("healthData")
fun bindRecyclerViewWithHealthData(recyclerView: RecyclerView, SaveDataHealthItem: List<Health>?) {
    SaveDataHealthItem?.let {
        recyclerView.adapter?.apply {
            when (this) {
                is SaveDataHealthAdapter -> submitList(it)
            }
        }
    }
}

@BindingAdapter("remindData")
fun bindRecyclerViewWithRemindData(recyclerView: RecyclerView, SaveDataRemindItem: List<Remind>?) {
    SaveDataRemindItem?.let {
        recyclerView.adapter?.apply {
            when (this) {
                is SaveDataRemindAdapter -> submitList(it)
            }
        }
    }
}

@BindingAdapter("homeData")
fun bindRecyclerViewWithHomeData(recyclerView: RecyclerView, SaveDataRemindItem: List<Remind>?) {
    SaveDataRemindItem?.let {
        recyclerView.adapter?.apply {
            when (this) {
                is HomeAdapter -> submitList(it)
            }
        }
    }
}
