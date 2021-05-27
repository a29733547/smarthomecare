package com.czerny.smarthomecare.data.source.local

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.*
import com.czerny.smarthomecare.data.source.SmartHomeCareDataSource

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

    override suspend fun deleteHealth(health: Health): Result<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getHealth(): Result<List<Health>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getHealthModify(id: String): Result<Health> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLiveHealth(): MutableLiveData<List<Health>> {
        TODO("Not yet implemented")
    }

    override fun getLiveHealthModify(): MutableLiveData<Health> {
        TODO("Not yet implemented")
    }
//    override fun getLiveHealthModify(): MutableLiveData<List<Health>> {
//        TODO("Not yet implemented")
//    }


    override suspend fun healthModify(health: Health): Result<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /**get remind data*/
    override suspend fun getRemind(): Result<List<Remind>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLiveRemind(): MutableLiveData<List<Remind>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProfile(): Result<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun addHealthDate(health: Health): Result<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }






}