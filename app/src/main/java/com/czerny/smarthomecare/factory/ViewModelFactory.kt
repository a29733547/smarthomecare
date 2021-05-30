package com.czerny.smarthomecare.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.czerny.smarthomecare.addremind.item.AddHealthEditViewModel
import com.czerny.smarthomecare.addremind.item.AddRemindEditViewModel

import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.home.HomeViewModel
import com.czerny.smarthomecare.profile.ProfileViewModel
import com.czerny.smarthomecare.profile.add.ProfileAddDataViewModel
import com.czerny.smarthomecare.savedata.modify.SaveDataRemindModifyViewModel
import com.czerny.smarthomecare.savedata.modify.SaveDataHealthModifyViewModel

import com.czerny.smarthomecare.savedata.remind.SaveDataHealthViewModel
import com.czerny.smarthomecare.savedata.remind.SaveDataRemindViewModel

/**
 * Created by Wayne Chen on 2020-01-15.
 *
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val repository: SmartHomeCareRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {

//                isAssignableFrom(SaveDataRemindModifyViewModel::class.java) ->
//                    SaveDataRemindModifyViewModel(repository)

                isAssignableFrom(SaveDataHealthModifyViewModel::class.java) ->
                    SaveDataHealthModifyViewModel(repository)

                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(repository)

                isAssignableFrom(SaveDataHealthViewModel::class.java) ->
                    SaveDataHealthViewModel(repository)

                isAssignableFrom(SaveDataRemindViewModel::class.java) ->
                    SaveDataRemindViewModel(repository)

                isAssignableFrom(AddHealthEditViewModel::class.java) ->
                    AddHealthEditViewModel(repository)

                isAssignableFrom(AddRemindEditViewModel::class.java) ->
                    AddRemindEditViewModel(repository)

                isAssignableFrom(ProfileViewModel::class.java) ->
                    ProfileViewModel(repository)

                isAssignableFrom(ProfileAddDataViewModel::class.java) ->
                    ProfileAddDataViewModel(repository)



                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
