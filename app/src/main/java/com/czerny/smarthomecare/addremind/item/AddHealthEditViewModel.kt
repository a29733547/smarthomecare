package com.czerny.smarthomecare.addremind.item



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.czerny.smarthomecare.data.Health




class AddHealthEditViewModel : ViewModel() {



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

