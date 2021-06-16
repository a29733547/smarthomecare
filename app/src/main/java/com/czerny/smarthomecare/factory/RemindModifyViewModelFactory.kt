package com.czerny.smarthomecare.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.savedata.detail.SaveDataRemindModifyViewModel

@Suppress("UNCHECKED_CAST")
class RemindModifyViewModelFactory(
    private val repository: SmartHomeCareRepository,
    private val remind: Remind,
    private val family: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SaveDataRemindModifyViewModel::class.java)) {
            return SaveDataRemindModifyViewModel(repository, remind, family) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}