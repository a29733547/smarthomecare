package com.czerny.smarthomecare.data.source.remote

import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Author
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.source.SmartHomeCareDataSource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.czerny.smarthomecare.data.Result
import com.czerny.smarthomecare.util.Logger

object SmartHomeCareRemoteDataSource : SmartHomeCareDataSource {

    override suspend fun login(id: String): Result<Author> {
        TODO("Not yet implemented")
    }

    private const val PATH_ARTICLES = "healthDate"
    private const val KEY_CREATED_TIME = "createdTime"

    override suspend fun getHealth(): Result<List<Health>> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_ARTICLES)
            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list = mutableListOf<Health>()
                    for (document in task.result!!) {
                        Logger.d(document.id + " => " + document.data)

                        val health = document.toObject(Health::class.java)
                        list.add(health)
                    }
                    continuation.resume(Result.Success(list))
                } else {
                    task.exception?.let {

                        Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                        continuation.resume(Result.Error(it))
                        return@addOnCompleteListener
                    }
                    continuation.resume(Result.Fail(SmartHomeCareApplication.instance.getString(R.string.you_know_nothing)))
                }
            }
    }



}