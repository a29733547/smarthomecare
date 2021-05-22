package com.czerny.smarthomecare.addremind.item



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


//class AddHealthEditViewModel : ViewModel() {
class AddHealthEditViewModel(private val repository: SmartHomeCareRepository) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //沒有好像也可以？
    val healthId = MutableLiveData<String>()
    val healthTitle = MutableLiveData<String>()
    val healthPlace = MutableLiveData<String>()
    val healthContent = MutableLiveData<String>()
    val healthName = MutableLiveData<String>()
    val healthNote = MutableLiveData<String>()


    val health = Health(
        healthId.value ?: "",
        healthPlace.value ?: "",
        healthTitle.value ?: "",
        healthName.value ?: "",
        healthContent.value ?: "",
        healthNote.value ?: "",
    )



}

