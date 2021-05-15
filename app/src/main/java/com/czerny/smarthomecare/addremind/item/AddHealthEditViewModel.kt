package com.czerny.smarthomecare.addremind.item

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class AddHealthEditViewModel : ViewModel(){

//    private val _getRemindTimeData = MutableLiveData<String>()
//    val getRemindTimeData: LiveData<String>
//        get() = _getRemindTimeData
    var getRemindTimeData = ""
//      var getRemindTimeData: MutableLiveData<String>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

}

