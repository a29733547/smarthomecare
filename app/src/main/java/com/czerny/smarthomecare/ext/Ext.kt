package com.czerny.smarthomecare.ext

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import com.czerny.smarthomecare.data.Health
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

/**
 * Created by Wayne Chen on 2020-01-15.
 */
/*fun Long.toDisplayFormat(): String {
    return SimpleDateFormat("yyyy.MM.dd hh:mm", Locale.TAIWAN).format(this)
}*/

fun updateHeaalth(health: Health) {
    val articles = FirebaseFirestore.getInstance()
        .collection("healtheDate")

    val document = articles.document()

    val data = hashMapOf(
        "title" to health.title,
        "name" to health.name,
        "healthPlaceData" to health.healthPlaceData,
        "content" to health.content,
        "note" to health.note
    )
    document.set(data)
}