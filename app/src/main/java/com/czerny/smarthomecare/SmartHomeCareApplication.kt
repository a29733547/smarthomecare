package com.czerny.smarthomecare

import android.app.Application
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.util.ServiceLocator
import kotlin.properties.Delegates

class SmartHomeCareApplication : Application() {

    // Depends on the flavor,
    val repository: SmartHomeCareRepository
        get() = ServiceLocator.provideRepository(this)

    companion object {
        var instance: SmartHomeCareApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun isLiveDataDesign() = true
}