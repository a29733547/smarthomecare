package com.czerny.smarthomecare.profile.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.data.Profile
import com.czerny.smarthomecare.data.User
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository

class ProfileAddDataViewModel (private val repository: SmartHomeCareRepository): ViewModel() {


    val userProfile = Profile(
        id = "",
        name = "",
        birth = "",
        year = "",
        weight = "",
        blood = "",
        genetic = "",
        allergy = "",
        note = "",

    )
}