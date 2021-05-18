package com.czerny.smarthomecare.data.source.local

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.Author
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.source.SmartHomeCareDataSource
import com.czerny.smarthomecare.data.Result

class SmartHomeCareLocalDataSource (val context: Context) : SmartHomeCareDataSource {
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

    override suspend fun getHealth(): Result<List<Health>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLiveHealth(): MutableLiveData<List<Health>> {
        TODO("Not yet implemented")
    }

    override suspend fun smart(health: Health): Result<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}