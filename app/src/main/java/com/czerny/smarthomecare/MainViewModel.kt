package com.czerny.smarthomecare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.util.CurrentFragmentType

class MainViewModel: ViewModel() {
//    class MainViewModel (private val repository: SmartHomeCareRepository): ViewModel() {

    private val _getRemindTimeData = MutableLiveData<String>()
    val getRemindTimeData: LiveData<String>
        get() = _getRemindTimeData

//    val currentFragmentType = MutableLiveData<CurrentFragmentType>()

}