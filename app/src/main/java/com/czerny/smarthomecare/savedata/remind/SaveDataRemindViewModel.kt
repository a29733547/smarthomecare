package com.czerny.smarthomecare.savedata.remind

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.MockData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SaveDataRemindViewModel : ViewModel(){
    var editableList: MutableList<MockData> = mutableListOf()

    private val _Mockdata = MutableLiveData<List<MockData>>()
    val Mockdata: LiveData<List<MockData>>
        get() = _Mockdata

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)
}