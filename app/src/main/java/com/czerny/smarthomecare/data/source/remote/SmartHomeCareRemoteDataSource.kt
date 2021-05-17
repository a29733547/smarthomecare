package com.czerny.smarthomecare.data.source.remote


import androidx.lifecycle.MutableLiveData
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

    override fun getLiveHealth(): MutableLiveData<List<Health>> {

        val liveData = MutableLiveData<List<Health>>()

        FirebaseFirestore.getInstance()
            .collection(PATH_ARTICLES)
            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, exception ->

                Logger.i("addSnapshotListener detect")

                exception?.let {
                    Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                }

                val list = mutableListOf<Health>()
                for (document in snapshot!!) {
                    Logger.d(document.id + " => " + document.data)

                    val article = document.toObject(Health::class.java)
                    list.add(article)
                }

                liveData.value = list
            }
        return liveData
    }


    override suspend fun smart(health: Health): Result<Boolean> = suspendCoroutine { continuation ->
        val articles = FirebaseFirestore.getInstance().collection(PATH_ARTICLES)
        val document = articles.document()

        health.id = document.id
//        article.createdTime = Calendar.getInstance().timeInMillis

        document
            .set(health)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Logger.i("Publish: $health")

                    continuation.resume(Result.Success(true))
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