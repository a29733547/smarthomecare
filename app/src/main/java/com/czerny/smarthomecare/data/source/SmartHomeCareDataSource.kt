package com.czerny.smarthomecare.data.source

import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.Author
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Result

interface SmartHomeCareDataSource {

    suspend fun login(id: String): Result<Author>

    suspend fun getHealth(): Result<List<Health>>

    fun getLiveHealth(): MutableLiveData<List<Health>>

    suspend fun smart(health: Health): Result<Boolean>

}
