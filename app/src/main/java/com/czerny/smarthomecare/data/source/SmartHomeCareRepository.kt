package com.czerny.smarthomecare.data.source

import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.*

interface SmartHomeCareRepository {

    suspend fun deleteHealth(health: Health): Result<Boolean>

    suspend fun deleteRemind(remind: Remind): Result<Boolean>

    suspend fun getHealth(): Result<List<Health>>

    suspend fun getHealthModify(id: String): Result<Health>

    fun getLiveHealth(): MutableLiveData<List<Health>>

    fun getLiveHealthModify(): MutableLiveData<Health>
//    fun getLiveHealthModify(): MutableLiveData<List<Health>>

    suspend fun healthModify(health: Health): Result<Boolean>

    suspend fun remindModify(remind: Remind): Result<Boolean>

    suspend fun getRemind(): Result<List<Remind>>

    fun getLiveRemind(): MutableLiveData<List<Remind>>

    fun getLiveRemindModify(): MutableLiveData<Remind>

    suspend fun getProfile(): Result<User>

    //add頁面
    suspend fun addHealthData(health: Health): Result<Boolean>

    //add頁面
    suspend fun addRemindData(remind: Remind): Result<Boolean>

    suspend fun postMessage(emails: List<String>, message: Message): Result<Boolean>

    fun getAllLiveMessage (emails: List<String>) : MutableLiveData<List<Message>>


}