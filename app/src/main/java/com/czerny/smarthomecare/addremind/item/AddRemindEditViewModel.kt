package com.czerny.smarthomecare.addremind.item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class AddRemindEditViewModel (private val repository: SmartHomeCareRepository): ViewModel(){

    //    private val _getRemindTimeData = MutableLiveData<String>()
//    val getRemindTimeData: LiveData<String>
//        get() = _getRemindTimeData
    var getRemindTimeData = ""
    var editableList: MutableList<String> = mutableListOf()
//      var getRemindTimeData: MutableLiveData<String>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    //沒有好像也可以？
    val remindPushId = MutableLiveData<String>()
    val remindPushName = MutableLiveData<String>()
    val remindPushHours = MutableLiveData<String>()
    val remindPushTheMinute = MutableLiveData<String>()
    val remindPushData = MutableLiveData<String>()
    val remindPushContent = MutableLiveData<String>()
    val remindPushNote = MutableLiveData<String>()

//    val remindPushId = MutableLiveData<String>()
//    val remindPushName = MutableLiveData<String>()
//    val remindPushHours = MutableLiveData<String>()
//    val remindPushMinute = MutableLiveData<String>()
//    val remindPushData = MutableLiveData<String>()
//    val remindPushContent = MutableLiveData<String>()
//    val remindPushNote = MutableLiveData<String>()



    val remindPush = Remind(
        remindPushId.value ?: "",
        remindPushName.value ?: "",
        remindPushHours.value ?: "",
        remindPushTheMinute.value ?: "",
        remindPushData.value ?: "",
        remindPushContent.value ?: "",
        remindPushNote.value ?: "",
    )

//    val remindPush = Remind(
//        remindPushId.value ?: "",
//        remindPushName.value ?: "",
//        remindPushHours.value ?: "",
//        remindPushMinute.value ?: "",
//        remindPushData.value ?: "",
//        remindPushContent.value ?: "",
//        remindPushNote.value ?: "",
//
//        )

}
