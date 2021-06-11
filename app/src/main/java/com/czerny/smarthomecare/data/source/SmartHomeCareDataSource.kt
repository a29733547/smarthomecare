package com.czerny.smarthomecare.data.source

import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.*
import com.google.firebase.auth.FirebaseUser

interface SmartHomeCareDataSource {

    suspend fun login(id: String): Result<Author>

    suspend fun deleteHealth(health: Health): Result<Boolean>

    suspend fun deleteRemind(remind: Remind): Result<Boolean>

    suspend fun getHealth(): Result<List<Health>>

    suspend fun getHealthModify(id: String): Result<Health>

    fun getLiveHealth(): MutableLiveData<List<Health>>

    fun getLiveHealthModify(): MutableLiveData<Health>
//    fun getLiveHealthModify(): MutableLiveData<List<Health>>

    suspend fun healthModify(health: Health): Result<Boolean>

    suspend fun remindModify(remind: Remind): Result<Boolean>
    fun getLiveRemindModify(): MutableLiveData<Remind>

    /**--------get remind data--------*/
    suspend fun getRemind(family: String): Result<List<Remind>>
    fun getLiveRemind(family: String): MutableLiveData<List<Remind>>
    /**--------get remind data--------*/

    suspend fun getProfile(): Result<User>

    /**--------add data--------*/
    suspend fun addHealthData(health: Health): Result<Boolean>
    suspend fun addRemindData(remind: Remind, family: String): Result<Boolean>
    /**--------add data--------*/

//    suspend fun postMessage(emails: List<String>, chatRoom: ChatRoom, family: String): Result<Boolean>

    /**--------chatroom--------*/
    suspend fun postMessage(userId: String, message: String, family: String): Result<Boolean>
    fun getAllLiveMessage(emails: List<String>,family: String): MutableLiveData<List<ChatRoom>>


    /**--------chatroom--------*/

    /**--------add user & family--------*/
    fun getUserList(): MutableLiveData<List<UserInfo>>
//    fun getFamilyList(): MutableLiveData<FamilyInfo>
    fun getFamilyList(): MutableLiveData<List<FamilyInfo>>
    suspend fun pushFamily(user: User?): Result<Boolean>
    /**--------add user & family--------*/



    suspend fun firebaseAuthWithGoogle(idToken: String): Result<FirebaseUser>

    suspend fun postUser(user: User): Result<Boolean>
    suspend fun getUser(): Result<User>
}
