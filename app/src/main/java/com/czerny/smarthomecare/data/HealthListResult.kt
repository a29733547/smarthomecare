package com.czerny.smarthomecare.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HealthListResult(
    val error: String? = null,
    @Json(name = "data") val healths: List<Health>? = null
) : Parcelable
