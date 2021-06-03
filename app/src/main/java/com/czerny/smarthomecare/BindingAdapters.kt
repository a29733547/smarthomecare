package com.czerny.smarthomecare

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.home.HomeAdapter
import com.czerny.smarthomecare.network.LoadApiStatus
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


//
//@BindingAdapter("timeToDisplayFormat")
//fun bindDisplayFormatTime(textView: TextView, time: Long?) {
//    textView.text = time?.toDisplayFormat()
//}


/**
 * According to [LoadApiStatus] to decide the visibility of [ProgressBar]
 */
@BindingAdapter("setupApiStatus")
fun bindApiStatus(view: ProgressBar, status: LoadApiStatus?) {
    when (status) {
        LoadApiStatus.LOADING -> view.visibility = View.VISIBLE
        LoadApiStatus.DONE, LoadApiStatus.ERROR -> view.visibility = View.GONE
    }
}

/**
 * According to [message] to decide the visibility of [ProgressBar]
 */
@BindingAdapter("setupApiErrorMessage")
fun bindApiErrorMessage(view: TextView, message: String?) {
    when (message) {
        null, "" -> {
            view.visibility = View.GONE
        }
        else -> {
            view.text = message
            view.visibility = View.VISIBLE
        }
    }
}