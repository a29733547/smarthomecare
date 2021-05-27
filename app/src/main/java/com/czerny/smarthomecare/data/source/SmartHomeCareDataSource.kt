package com.czerny.smarthomecare.data.source

import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.*

interface SmartHomeCareDataSource {

    suspend fun login(id: String): Result<Author>

    suspend fun deleteHealth(health: Health): Result<Boolean>
    
    suspend fun getHealth(): Result<List<Health>>

    suspend fun getHealthModify(id: String): Result<Health>

    fun getLiveHealth(): MutableLiveData<List<Health>>

    fun getLiveHealthModify(): MutableLiveData<Health>
//    fun getLiveHealthModify(): MutableLiveData<List<Health>>

    suspend fun healthModify(health: Health): Result<Boolean>

    suspend fun getRemind(): Result<List<Remind>>
    
    fun getLiveRemind(): MutableLiveData<List<Remind>>


    suspend fun getProfile(): Result<User>

    suspend fun addHealthDate(health: Health): Result<Boolean>



}
