package com.czerny.smarthomecare.addremind

import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository

class AddRemindViewModel (private val repository: SmartHomeCareRepository, family: String) : ViewModel() {

    val familyName = family

}