package com.czerny.smarthomecare.data.source

import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Result

class DefaultSmartHomeCareRepository (private val remoteDataSource: SmartHomeCareDataSource,
                                      private val localDataSource: SmartHomeCareDataSource
) : SmartHomeCareRepository {

    override suspend fun getHealth(): Result<List<Health>> {
        return remoteDataSource.getHealth()
    }

    override fun getLiveHealth(): MutableLiveData<List<Health>> {
        return remoteDataSource.getLiveHealth()
    }

    override suspend fun smart(health: Health): Result<Boolean> {
        return remoteDataSource.smart(health)
    }
}