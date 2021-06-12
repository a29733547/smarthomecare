package com.czerny.smarthomecare.savedata

import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository

class SaveDataViewModel (private val repository: SmartHomeCareRepository, family: String) : ViewModel() {

    val familyName = family

}