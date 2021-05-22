package com.czerny.smarthomecare.data.source

import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.*

interface SmartHomeCareDataSource {

    suspend fun login(id: String): Result<Author>
    

    suspend fun getHealth(): Result<List<Health>>

    suspend fun getRemind(): Result<List<Remind>>

    
    fun getLiveRemind(): MutableLiveData<List<Remind>>

    fun getLiveHealth(): MutableLiveData<List<Health>>

    suspend fun smart(health: Health): Result<Boolean>

    suspend fun smartRemind(remind: Remind): Result<Boolean>

    suspend fun getProfile(): Result<User>

/*    fun getLiveProfile(): MutableLiveData<User>

    suspend fun smartProfile(user: User): Result<Boolean>*/


/*    suspend fun getHome(): Result<List<Remind>>

    fun getLiveHome(): MutableLiveData<List<Remind>>

    suspend fun smartHome(remind: Remind): Result<Boolean>*/



}
