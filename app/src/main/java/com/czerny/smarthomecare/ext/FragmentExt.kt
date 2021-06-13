package com.czerny.smarthomecare.ext

import androidx.fragment.app.Fragment

import com.czerny.smarthomecare.factory.ViewModelFactory
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.factory.FamilyViewModelFactory
import com.czerny.smarthomecare.factory.HealthModifyViewModelFactory

import com.czerny.smarthomecare.factory.RemindModifyViewModelFactory


/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Extension functions for Fragment.
 */
fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as SmartHomeCareApplication).repository
    return ViewModelFactory(repository)
}

fun Fragment.getVmFactory(remind: Remind, family: String): RemindModifyViewModelFactory {
    val repository = (requireContext().applicationContext as SmartHomeCareApplication).repository
    return RemindModifyViewModelFactory(repository, remind, family)
}

fun Fragment.getVmFactory(health: Health, family: String):  HealthModifyViewModelFactory{
    val repository = (requireContext().applicationContext as SmartHomeCareApplication).repository
    return HealthModifyViewModelFactory(repository, health, family)

}

fun Fragment.getVmFactory(family: String): FamilyViewModelFactory {
    val repository = (requireContext().applicationContext as SmartHomeCareApplication).repository
    return FamilyViewModelFactory(repository, family)
}