package com.czerny.smarthomecare.data.source

import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.*
import com.google.firebase.auth.FirebaseUser

interface SmartHomeCareDataSource {



    suspend fun deleteHealth(health: Health, family: String): Result<Boolean>
    suspend fun deleteRemind(remind: Remind, family: String  ): Result<Boolean>


    suspend fun healthModify(health: Health, family: String): Result<Boolean>
    suspend fun remindModify(remind: Remind, family: String ): Result<Boolean>

    /**--------get health data--------*/
    fun getLiveHealth(family: String): MutableLiveData<List<Health>>
    suspend fun getHealth(family: String): Result<List<Health>>
    /**--------get health data--------*/

    /**--------get remind data--------*/
    suspend fun getRemind(family: String): Result<List<Remind>>
    fun getLiveRemind(family: String): MutableLiveData<List<Remind>>
    /**--------get remind data--------*/



    suspend fun getProfile(): Result<Profile>

    /**--------add data--------*/
    suspend fun addHealthData(health: Health, family: String): Result<Boolean>
    suspend fun addRemindData(remind: Remind, family: String): Result<Boolean>
    /**--------add data--------*/

//    suspend fun postMessage(emails: List<String>, chatRoom: ChatRoom, family: String): Result<Boolean>

    /**--------chatroom--------*/
    suspend fun postMessage(userId: String, message: String, family: String): Result<Boolean>
    fun getAllLiveMessage(emails: List<String>,family: String): MutableLiveData<List<ChatRoom>>


    /**--------chatroom--------*/

    /**--------add user & family--------*/
    fun getFamilyList(): MutableLiveData<List<FamilyInfo>>
    suspend fun pushFamily(user: User?): Result<Boolean>
    /**--------add user & family--------*/



    suspend fun firebaseAuthWithGoogle(idToken: String): Result<FirebaseUser>

    suspend fun postUser(user: User): Result<Boolean>
    suspend fun getUser(): Result<User>
}
