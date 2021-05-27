package com.czerny.smarthomecare.ext

import androidx.fragment.app.Fragment

import com.czerny.smarthomecare.factory.ViewModelFactory
import com.czerny.smarthomecare.SmartHomeCareApplication



/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Extension functions for Fragment.
 */
fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as SmartHomeCareApplication).repository
    return ViewModelFactory(repository)
}

//fun Fragment.getVmFactory(health: Health?): HealthModifyViewModelFactory {
//    val repository = (requireContext().applicationContext as SmartHomeCareApplication).repository
//    return HealthModifyViewModelFactory(repository, health)
//}