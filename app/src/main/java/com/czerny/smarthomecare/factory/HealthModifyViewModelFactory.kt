//package com.czerny.smarthomecare.factory
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//
//import com.czerny.smarthomecare.data.Author
//import com.czerny.smarthomecare.data.Health
//import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
//import com.czerny.smarthomecare.savedata.modify.SaveDataHealthModifyViewModel
//
///**
// * Created by Wayne Chen on 2020-01-15.
// *
// * Factory for all ViewModels which need [Author].
// */
//@Suppress("UNCHECKED_CAST")
//class HealthModifyViewModelFactory(
//    private val repository: SmartHomeCareRepository,
//    private val health: Health?
//) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//
//        if (modelClass.isAssignableFrom(SaveDataHealthModifyViewModel::class.java)) {
//            return SaveDataHealthModifyViewModel(repository, health) as T
//        }
//
//        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
//    }
//}