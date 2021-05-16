package com.czerny.smarthomecare.addremind.item

import androidx.lifecycle.ViewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class AddRemindEditViewModel : ViewModel(){

    //    private val _getRemindTimeData = MutableLiveData<String>()
//    val getRemindTimeData: LiveData<String>
//        get() = _getRemindTimeData
    var getRemindTimeData = ""
    var editableList: MutableList<String> = mutableListOf()
//      var getRemindTimeData: MutableLiveData<String>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

}
