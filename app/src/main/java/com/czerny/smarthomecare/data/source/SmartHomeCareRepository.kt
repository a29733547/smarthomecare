package com.czerny.smarthomecare.data.source

import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Result

interface SmartHomeCareRepository {

    suspend fun getHealth(): Result<List<Health>>

    fun getLiveHealth(): MutableLiveData<List<Health>>

    suspend fun smart(health: Health): Result<Boolean>

}