package com.czerny.smarthomecare.ext

import android.app.Activity
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.factory.ViewModelFactory
fun Activity.getVmFactory(): ViewModelFactory {
    val repository = (applicationContext as SmartHomeCareApplication).repository
    return ViewModelFactory(repository)
}