package com.czerny.smarthomecare.data.source

import com.czerny.smarthomecare.data.Author
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Result

interface SmartHomeCareDataSource {


    suspend fun getHealth(): Result<List<Health>>

    suspend fun login(id: String): Result<Author>
}
