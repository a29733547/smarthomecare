package com.czerny.smarthomecare.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.czerny.smarthomecare.MainViewModel

import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.login.LoginViewModel
import com.czerny.smarthomecare.profile.ProfileViewModel
import com.czerny.smarthomecare.profile.add.ProfileAddDataViewModel

import com.czerny.smarthomecare.savedata.remind.SaveDataHealthViewModel
import com.czerny.smarthomecare.savedata.remind.SaveDataRemindViewModel
import com.czerny.smarthomecare.userfamily.UserFamilyViewModel


/**
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val repository: SmartHomeCareRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {

                isAssignableFrom(UserFamilyViewModel::class.java) ->
                    UserFamilyViewModel(repository)

                isAssignableFrom(LoginViewModel::class.java) ->
                    LoginViewModel(repository)

                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(repository)

                isAssignableFrom(SaveDataHealthViewModel::class.java) ->
                    SaveDataHealthViewModel(repository)

                isAssignableFrom(SaveDataRemindViewModel::class.java) ->
                    SaveDataRemindViewModel(repository)

                isAssignableFrom(ProfileViewModel::class.java) ->
                    ProfileViewModel(repository)

                isAssignableFrom(ProfileAddDataViewModel::class.java) ->
                    ProfileAddDataViewModel(repository)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
