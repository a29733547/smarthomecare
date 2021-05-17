package com.czerny.smarthomecare.data.source

import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Result

class DefaultSmartHomeCareRepository (private val remoteDataSource: SmartHomeCareDataSource,
                                      private val localDataSource: SmartHomeCareDataSource
) : SmartHomeCareRepository {

    override suspend fun getHealth(): Result<List<Health>> {
        return remoteDataSource.getHealth()
    }
}