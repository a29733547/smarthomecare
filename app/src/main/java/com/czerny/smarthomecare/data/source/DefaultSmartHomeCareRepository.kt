package com.czerny.smarthomecare.data.source

import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.*
import com.google.firebase.auth.FirebaseUser

class DefaultSmartHomeCareRepository(
    private val remoteDataSource: SmartHomeCareDataSource,
    private val localDataSource: SmartHomeCareDataSource
) : SmartHomeCareRepository {

    override suspend fun deleteHealth(health: Health): Result<Boolean> {
        return remoteDataSource.deleteHealth(health)
    }

    override suspend fun deleteRemind(remind: Remind, family: String ): Result<Boolean> {
        return remoteDataSource.deleteRemind(remind, family)
    }

    override suspend fun getHealth(): Result<List<Health>> {
        return remoteDataSource.getHealth()
    }

    override suspend fun getHealthModify(id: String): Result<Health> {
        return remoteDataSource.getHealthModify(id)
    }

    override fun getLiveHealth(): MutableLiveData<List<Health>> {
        return remoteDataSource.getLiveHealth()
    }

    override fun getLiveHealthModify(): MutableLiveData<Health> {
        return remoteDataSource.getLiveHealthModify()
    }

    override suspend fun healthModify(health: Health): Result<Boolean> {
        return remoteDataSource.healthModify(health)
    }

    override suspend fun remindModify(remind: Remind): Result<Boolean> {
        return remoteDataSource.remindModify(remind)
    }

    /**--------get remind data--------*/
    override suspend fun getRemind(family: String): Result<List<Remind>> {
        return remoteDataSource.getRemind(family)
    }
    override fun getLiveRemind(family: String): MutableLiveData<List<Remind>> {
        return remoteDataSource.getLiveRemind(family)
    }
    /**--------get remind data--------*/
    override suspend fun getSaveRemind(): Result<List<Remind>> {
        return remoteDataSource.getSaveRemind()
    }
    override fun getLiveSaveRemind(): MutableLiveData<List<Remind>> {
        return remoteDataSource.getLiveSaveRemind()
    }



    override fun getLiveRemindModify(): MutableLiveData<Remind> {
        return remoteDataSource.getLiveRemindModify()
    }


    override suspend fun getProfile(): Result<User> {
        return remoteDataSource.getProfile()
    }

    /**--------add data--------*/
    override suspend fun addHealthData(health: Health): Result<Boolean> {
        return remoteDataSource.addHealthData(health)
    }
    override suspend fun addRemindData(remind: Remind, family: String): Result<Boolean> {
        return remoteDataSource.addRemindData(remind, family)
    }
    /**--------add data--------*/

//    override suspend fun postMessage(emails: List<String>, chatRoom: ChatRoom, family: String): Result<Boolean> {
//        return remoteDataSource.postMessage(emails, chatRoom, family)
//    }

    /**--------chatroom--------*/
    override suspend fun postMessage(userId: String, message: String, family: String): Result<Boolean> {
        return remoteDataSource.postMessage(userId, message, family)
    }
    override fun getAllLiveMessage(emails: List<String>, family: String): MutableLiveData<List<ChatRoom>> {
        return remoteDataSource.getAllLiveMessage(emails, family)
    }


    /**--------chatroom--------*/


    /**--------add user & family--------*/
    override fun getUserList(): MutableLiveData<List<UserInfo>> {
        return remoteDataSource.getUserList()
    }
//    override fun getFamilyList(): MutableLiveData<FamilyInfo>> {
//        return remoteDataSource.getFamilyList()
//    }
    override fun getFamilyList(): MutableLiveData<List<FamilyInfo>> {
        return remoteDataSource.getFamilyList()
    }
    override suspend fun pushFamily(user: User?): Result<Boolean> {
        return remoteDataSource.pushFamily(user)
    }
    /**--------add user & family--------*/



    override suspend fun firebaseAuthWithGoogle(idToken: String): Result<FirebaseUser> {
        return remoteDataSource.firebaseAuthWithGoogle(idToken)
    }

    override suspend fun postUser(user: User): Result<Boolean> {
        return remoteDataSource.postUser(user)
    }
    override suspend fun getUser(): Result<User> {
        return remoteDataSource.getUser()
    }

}