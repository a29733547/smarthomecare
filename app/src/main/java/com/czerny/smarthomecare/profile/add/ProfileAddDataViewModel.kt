package com.czerny.smarthomecare.profile.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.data.User
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository

class ProfileAddDataViewModel (private val repository: SmartHomeCareRepository): ViewModel() {

    //沒有好像也可以？
//    val profileId = MutableLiveData<String>()
//    val profileNameData = MutableLiveData<String>()
//    val profileBirth = MutableLiveData<String>()
//    val profilYear = MutableLiveData<String>()
//    val profileWeight = MutableLiveData<String>()
//    val profileBlood = MutableLiveData<String>()
//    val profileGenetic = MutableLiveData<String>()
//    val profileAllergy = MutableLiveData<String>()
//    val profileNote = MutableLiveData<String>()
//    val profileFamilyId = MutableLiveData<Int>()
//
//    val userProfile = User(
//        profileId.value ?: "",
//        profileNameData.value ?: "",
//        profileBirth.value ?: "",
//        profilYear.value ?: "",
//        profileWeight.value ?: "",
//        profileBlood.value ?: "",
//        profileGenetic.value ?: "",
//        profileAllergy.value ?: "",
//        profileNote.value ?: "",
//        profileFamilyId.value ?: 0,
//    )

    val userProfile = User(
        id = "",
        name = "",
        birth = "",
        year = "",
        weight = "",
        blood = "",
        genetic = "",
        allergy = "",
        note = "",
        familyId = 0
    )
}