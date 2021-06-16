package com.czerny.smarthomecare.util

import android.content.Context
import androidx.annotation.VisibleForTesting



import com.czerny.smarthomecare.data.source.DefaultSmartHomeCareRepository
import com.czerny.smarthomecare.data.source.SmartHomeCareDataSource
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.data.source.local.SmartHomeCareLocalDataSource
import com.czerny.smarthomecare.data.source.remote.SmartHomeCareRemoteDataSource

object ServiceLocator {

    @Volatile
    var repository: SmartHomeCareRepository? = null
        @VisibleForTesting set

    fun provideRepository(context: Context): SmartHomeCareRepository {
        synchronized(this) {
            return repository
                ?: repository
                ?: createPublisherRepository(context)
        }
    }

    private fun createPublisherRepository(context: Context): SmartHomeCareRepository {
        return DefaultSmartHomeCareRepository(
            SmartHomeCareRemoteDataSource,
            createLocalDataSource(context)
        )
    }

    private fun createLocalDataSource(context: Context): SmartHomeCareDataSource {
        return SmartHomeCareLocalDataSource(context)
    }
}