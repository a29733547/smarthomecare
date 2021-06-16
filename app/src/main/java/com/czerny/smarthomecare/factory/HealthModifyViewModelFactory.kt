package com.czerny.smarthomecare.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


import com.czerny.smarthomecare.data.Health

import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.savedata.detail.SaveDataHealthModifyViewModel

@Suppress("UNCHECKED_CAST")
class HealthModifyViewModelFactory(
    private val repository: SmartHomeCareRepository,
    private val health: Health,
    private val family: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SaveDataHealthModifyViewModel::class.java)) {
            return SaveDataHealthModifyViewModel(repository, health, family) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}