package com.czerny.smarthomecare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _getRemindTimeData = MutableLiveData<String>()
    val getRemindTimeData: LiveData<String>
        get() = _getRemindTimeData

}