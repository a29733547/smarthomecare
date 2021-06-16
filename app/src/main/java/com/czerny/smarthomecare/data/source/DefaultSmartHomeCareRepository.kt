package com.czerny.smarthomecare.data.source

import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.*
import com.google.firebase.auth.FirebaseUser

class DefaultSmartHomeCareRepository(
    private val remoteDataSource: SmartHomeCareDataSource,
    private val localDataSource: SmartHomeCareDataSource
) : SmartHomeCareRepository {

    override suspend fun deleteHealth(health: Health, family: String): Result<Boolean> {
        return remoteDataSource.deleteHealth(health, family)
    }

    override suspend fun deleteRemind(remind: Remind, family: String ): Result<Boolean> {
        return remoteDataSource.deleteRemind(remind, family)
    }






    override suspend fun healthModify(health: Health, family: String): Result<Boolean> {
        return remoteDataSource.healthModify(health, family)
    }
    override suspend fun remindModify(remind: Remind,  family: String): Result<Boolean> {
        return remoteDataSource.remindModify(remind, family)
    }


    /**--------get health data--------*/
    override fun getLiveHealth(family: String): MutableLiveData<List<Health>> {
        return remoteDataSource.getLiveHealth(family)
    }
    override suspend fun getHealth(family: String): Result<List<Health>> {
        return remoteDataSource.getHealth(family)
    }
    /**--------get health data--------*/

    /**--------get remind data--------*/
    override suspend fun getRemind(family: String): Result<List<Remind>> {
        return remoteDataSource.getRemind(family)
    }
    override fun getLiveRemind(family: String): MutableLiveData<List<Remind>> {
        return remoteDataSource.getLiveRemind(family)
    }
    /**--------get remind data--------*/



//    override fun getLiveRemindModify(): MutableLiveData<Remind> {
//        return remoteDataSource.getLiveRemindModify()
//    }


    override suspend fun getProfile(): Result<Profile> {
        return remoteDataSource.getProfile()
    }

    /**--------add data--------*/
    override suspend fun addHealthData(health: Health, family: String): Result<Boolean> {
        return remoteDataSource.addHealthData(health, family)
    }
    override suspend fun addRemindData(remind: Remind, family: String): Result<Boolean> {
        return remoteDataSource.addRemindData(remind, family)
    }
    /**--------add data--------*/

    /**--------chatroom--------*/
    override suspend fun postMessage(userId: String, message: String, family: String): Result<Boolean> {
        return remoteDataSource.postMessage(userId, message, family)
    }
    override fun getAllLiveMessage(emails: List<String>, family: String): MutableLiveData<List<ChatRoom>> {
        return remoteDataSource.getAllLiveMessage(emails, family)
    }


    /**--------chatroom--------*/


    /**--------add user & family--------*/

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