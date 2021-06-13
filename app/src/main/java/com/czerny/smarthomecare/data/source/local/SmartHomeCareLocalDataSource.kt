package com.czerny.smarthomecare.data.source.local

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.*
import com.czerny.smarthomecare.data.source.SmartHomeCareDataSource
import com.google.firebase.auth.FirebaseUser

class SmartHomeCareLocalDataSource(val context: Context) : SmartHomeCareDataSource {
    override suspend fun login(id: String): Result<Author> {
        TODO("Not yet implemented")
    }


//        override suspend fun login(id: String): Result<Author> {
//            return when (id) {
//                "waynechen323" -> Result.Success((Author(
//                    id,
//                    "AKA小安老師",
//                    "wayne@school.appworks.tw"
//                )))
//                "dlwlrma" -> Result.Success((Author(
//                    id,
//                    "IU",
//                    "dlwlrma@school.appworks.tw"
//                )))
//                //TODO add your profile here
//                else -> Result.Fail("You have to add $id info in local data source")
//            }
//        }

    override suspend fun deleteHealth(health: Health, family: String): Result<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override suspend fun deleteRemind(remind: Remind, family: String  ): Result<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override suspend fun getHealthModify(): Result<Health> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getLiveHealthModify(): MutableLiveData<Health> {
        TODO("Not yet implemented")
    }


    /**--------get health data--------*/
    override fun getLiveHealth(family: String): MutableLiveData<List<Health>> {
        TODO("Not yet implemented")
    }
    override suspend fun getHealth(family: String): Result<List<Health>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    /**--------get health data--------*/


    override suspend fun healthModify(health: Health, family: String): Result<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override suspend fun remindModify(remind: Remind, family: String): Result<Boolean> {
        TODO("Not yet implemented")
    }


    /**get remind data*/
    override suspend fun getRemind(family: String): Result<List<Remind>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getLiveRemind(family: String): MutableLiveData<List<Remind>> {
        TODO("Not yet implemented")
    }
    /**get remind data*/
    override suspend fun getSaveRemind(): Result<List<Remind>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getLiveSaveRemind(): MutableLiveData<List<Remind>> {
        TODO("Not yet implemented")
    }
    override fun getLiveRemindModify(): MutableLiveData<Remind> {
        TODO("Not yet implemented")
    }

    override suspend fun getProfile(): Result<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**--------add data--------*/
    override suspend fun addHealthData(health: Health, family: String): Result<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override suspend fun addRemindData(remind: Remind, family: String): Result<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    /**--------add data--------*/




//    override suspend fun postMessage(emails: List<String>, chatRoom: ChatRoom, family: String): Result<Boolean> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    /**--------chatroom--------*/
    override suspend fun postMessage(userId: String, message: String, family: String): Result<Boolean> {
        TODO("Not yet implemented")
    }
    override fun getAllLiveMessage(emails: List<String>, family: String): MutableLiveData<List<ChatRoom>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /**--------chatroom--------*/

    /**--------add user & family--------*/
    override fun getUserList(): MutableLiveData<List<UserInfo>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
//    override fun getFamilyList(): MutableLiveData<FamilyInfo> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
    override fun getFamilyList(): MutableLiveData<List<FamilyInfo>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun pushFamily(user: User?): Result<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    /**--------add user & family--------*/


    override suspend fun firebaseAuthWithGoogle(idToken: String): Result<FirebaseUser> {
        TODO("Not yet implemented")
    }

    override suspend fun postUser(user: User): Result<Boolean> {
        TODO("Not yet implemented")
    }
    override suspend fun getUser(): Result<User> {
        TODO("Not yet implemented")
    }

}