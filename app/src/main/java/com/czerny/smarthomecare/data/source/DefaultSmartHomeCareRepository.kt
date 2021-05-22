package com.czerny.smarthomecare.data.source

import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.data.Result
import com.czerny.smarthomecare.data.User

class DefaultSmartHomeCareRepository (private val remoteDataSource: SmartHomeCareDataSource,
                                      private val localDataSource: SmartHomeCareDataSource
) : SmartHomeCareRepository {

    override suspend fun getHealth(): Result<List<Health>> {
        return remoteDataSource.getHealth()
    }

    override fun getLiveHealth(): MutableLiveData<List<Health>> {
        return remoteDataSource.getLiveHealth()
    }

    override suspend fun getRemind(): Result<List<Remind>> {
        return remoteDataSource.getRemind()
    }

    override fun getLiveRemind(): MutableLiveData<List<Remind>> {
        return remoteDataSource.getLiveRemind()
    }

    override suspend fun smart(health: Health): Result<Boolean> {
        return remoteDataSource.smart(health)
    }

    override suspend fun smartRemind(remind: Remind): Result<Boolean> {
        return remoteDataSource.smartRemind(remind)
    }

    override suspend fun getProfile(): Result<User> {
        return remoteDataSource.getProfile()
    }

/*    override fun getLiveProfile(): MutableLiveData<User> {
        return remoteDataSource.getLiveProfile()
    }
    override suspend fun smartProfile(user: User): Result<Boolean> {
        return remoteDataSource.smartProfile(user)
    }*/


/*    override suspend fun getHome(): Result<List<Remind>> {
        return remoteDataSource.getHome()
    }

    override fun getLiveHome(): MutableLiveData<List<Remind>> {
        return remoteDataSource.getLiveHome()
    }
    override suspend fun smartHome(remind: Remind): Result<Boolean> {
        return remoteDataSource.smartHome(remind)
    }*/


}