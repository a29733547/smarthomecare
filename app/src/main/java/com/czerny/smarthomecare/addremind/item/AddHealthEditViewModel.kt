package com.czerny.smarthomecare.addremind.item


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.czerny.smarthomecare.data.Health

import com.google.firebase.firestore.FirebaseFirestore


class AddHealthEditViewModel : ViewModel() {
    val db = FirebaseFirestore.getInstance()

//    private val _healthData = MutableLiveData<List<Health>>() //w2p2
//    val healthData: LiveData<List<Health>>
//        get() = _healthData
//    val healthData = MutableLiveData<List<Health>>()


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

//    override fun onCleared() {
//
//        val user: MutableMap<Health, Any> = HashMap()
//        user[Health(
//            0,
//            "",
//            "",
//            "",
//            "",
//            "",
//        )]
//
//
//        super.onCleared()
//    }


}

