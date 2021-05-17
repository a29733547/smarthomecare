package com.czerny.smarthomecare.data.source

import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Result

interface SmartHomeCareRepository {
    suspend fun getHealth(): Result<List<Health>>
}