package com.czerny.smarthomecare.data.source

import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.data.Result
import com.czerny.smarthomecare.data.User

class DefaultSmartHomeCareRepository (private val remoteDataSource: SmartHomeCareDataSource,
                                      private val localDataSource: SmartHomeCareDataSource
) : SmartHomeCareRepository {

    override suspend fun deleteHealth(health: Health): Result<Boolean> {
        return remoteDataSource.deleteHealth(health)
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
//override fun getLiveHealthModify(): MutableLiveData<List<Health>> {
//    return remoteDataSource.getLiveHealthModify()
//}

    override suspend fun healthModify(health: Health): Result<Boolean> {
        return remoteDataSource.healthModify(health)
    }

    override suspend fun getRemind(): Result<List<Remind>> {
        return remoteDataSource.getRemind()
    }

    override fun getLiveRemind(): MutableLiveData<List<Remind>> {
        return remoteDataSource.getLiveRemind()
    }

    override suspend fun getProfile(): Result<User> {
        return remoteDataSource.getProfile()
    }

    override suspend fun addHealthData(health: Health): Result<Boolean> {
        return remoteDataSource.addHealthData(health)
    }

    override suspend fun addRemindData(remide: Remind): Result<Boolean> {
        return remoteDataSource.addRemindData(remide)
    }


}