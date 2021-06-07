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

    override suspend fun deleteRemind(remind: Remind): Result<Boolean> {
        return remoteDataSource.deleteRemind(remind)
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

    override suspend fun getRemind(): Result<List<Remind>> {
        return remoteDataSource.getRemind()
    }

    override fun getLiveRemind(): MutableLiveData<List<Remind>> {
        return remoteDataSource.getLiveRemind()
    }

    override fun getLiveRemindModify(): MutableLiveData<Remind> {
        return remoteDataSource.getLiveRemindModify()
    }


    override suspend fun getProfile(): Result<User> {
        return remoteDataSource.getProfile()
    }

    override suspend fun addHealthData(health: Health): Result<Boolean> {
        return remoteDataSource.addHealthData(health)
    }

    override suspend fun addRemindData(remind: Remind): Result<Boolean> {
        return remoteDataSource.addRemindData(remind)
    }

    override suspend fun postMessage(emails: List<String>, chatRoom: ChatRoom): Result<Boolean> {
        return remoteDataSource.postMessage(emails, chatRoom)
    }

    //    override fun getAllLiveMessage () : MutableLiveData<List<ChatRoom>> {
//        return remoteDataSource.getAllLiveMessage()
//    }
    override fun getAllLiveMessage(emails: List<String>): MutableLiveData<List<ChatRoom>> {
        return remoteDataSource.getAllLiveMessage(emails)
    }

    //    override fun getAllLiveMessage (id:String) : MutableLiveData<List<ChatRoom>> {
//        return remoteDataSource.getAllLiveMessage(id)
//    }
    override suspend fun getUser(userEmail: String): Result<User> {
        return remoteDataSource.getUser(userEmail)
    }

    override suspend fun postMessage(userId: String, message: String): Result<Boolean> {
        return remoteDataSource.postMessage(userId, message)
    }

    override suspend fun firebaseAuthWithGoogle(idToken: String): Result<FirebaseUser> {
        return remoteDataSource.firebaseAuthWithGoogle(idToken)
    }

    override suspend fun postUser(user: User): Result<Boolean> {
        return remoteDataSource.postUser(user)
    }

}