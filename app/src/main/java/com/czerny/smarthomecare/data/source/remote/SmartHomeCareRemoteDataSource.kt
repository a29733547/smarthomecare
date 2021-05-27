package com.czerny.smarthomecare.data.source.remote



import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.*
import com.czerny.smarthomecare.data.source.SmartHomeCareDataSource
import com.czerny.smarthomecare.login.UserManager.user
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.czerny.smarthomecare.util.Logger
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.SnapshotMetadata
import java.util.*
import kotlin.collections.HashMap

object SmartHomeCareRemoteDataSource : SmartHomeCareDataSource {


    private const val PATH_HEALTH = "healthDate"
    private const val PATH_REMIND = "remindDate"
    private const val PATH_PROFILE = "profileDate"
    private const val KEY_CREATED_TIME = "createdTime"
    private const val TEST_ID = "id"
    private const val INFO_ID1 = "info1"
    private const val INFO_DI2 = "info2"

    override suspend fun login(id: String): Result<Author> {
        TODO("Not yet implemented")
    }


/**--------delete save health-----------*/
    override suspend fun deleteHealth(health: Health): Result<Boolean> = suspendCoroutine { continuation ->

    val articles = FirebaseFirestore.getInstance().collection(PATH_HEALTH)
    val document = articles.document(health.id)

    health.id = document.id


    document
        .delete()
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

//        when {
//            health.author?.id == "waynechen323"
//                    && health.tag.toLowerCase(Locale.TAIWAN) != "test"
//                    && health.tag.trim().isNotEmpty() -> {
//
//                continuation.resume(Result.Fail("You know nothing!! ${health.author?.name}"))
//            }
//            else -> {
//                FirebaseFirestore.getInstance()
//                    .collection(PATH_HEALTH)
//                    .document()
//                    .delete()
//                    .addOnSuccessListener {
//                        Logger.i("Delete: $health")
//
//                        continuation.resume(Result.Success(true))
//                    }.addOnFailureListener {
//                        Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
//                        continuation.resume(Result.Error(it))
//                    }
//            }
//        }
    }
    /**--------delete save health-----------*/

    /** get health date*/
    override suspend fun getHealth(): Result<List<Health>> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_HEALTH)
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
            .collection(PATH_HEALTH)
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
                    Log.i("czerny", "list.add(article) = ${article}")
                }

                liveData.value = list

            }
        return liveData
    }

/*    override suspend fun smart(health: Health): Result<Boolean> = suspendCoroutine { continuation ->
        val healthdate = FirebaseFirestore.getInstance().collection(PATH_HEALTH)
        val document = healthdate.document()

        health.id = document.id
//        article.createdTime = Calendar.getInstance().timeInMillis

        document
            .set(health)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Logger.i("health: $health")

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
    }*/
    
    /** get health date*/

    /** get remind date*/
    override suspend fun getRemind(): Result<List<Remind>> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_REMIND)
            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list = mutableListOf<Remind>()
                    for (document in task.result!!) {
                        Logger.d(document.id + " => " + document.data)

                        val remind = document.toObject(Remind::class.java)
                        list.add(remind)
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

    override fun getLiveRemind(): MutableLiveData<List<Remind>> {

        val liveData = MutableLiveData<List<Remind>>()

        FirebaseFirestore.getInstance()
            .collection(PATH_REMIND)
            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, exception ->

                Logger.i("addSnapshotListener detect")

                exception?.let {
                    Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                }

                val list = mutableListOf<Remind>()
                for (document in snapshot!!) {
                    Logger.d(document.id + " => " + document.data)

                    val article = document.toObject(Remind::class.java)
                    list.add(article)
                }

                liveData.value = list
            }
        return liveData
    }

    override suspend fun healthModify(health: Health): Result<Boolean> = suspendCoroutine { continuation ->
        val remindDate = FirebaseFirestore.getInstance().collection(PATH_HEALTH)
        val document = remindDate.document()

        health.id = document.id
        health.createdTime = Calendar.getInstance().timeInMillis

                document
//                    .update("id", "Z9usq02R5SOlstL9AW3n")
            .set(health)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Logger.i("health: $health")

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


    override suspend fun getProfile(): Result<User> = suspendCoroutine { continuation ->
//        override suspend fun getProfile(): Result<List<User>> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_PROFILE)
//            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var list = User()

                    for (document in task.result!!) {
                        Logger.d(document.id + " => " + document.data)


                        val user = document.toObject(User::class.java)
                        list = user

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

    override suspend fun getHealthModify(id: String): Result<Health> = suspendCoroutine { continuation ->
//        override suspend fun getProfile(): Result<List<User>> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_HEALTH)
            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var list = Health()

                    for (document in task.result!!) {
                        Logger.d(document.id + " => " + document.data)

                        val health = document.toObject(Health::class.java)
                        list = health

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


    override fun getLiveHealthModify(): MutableLiveData<Health> {
//override fun getLiveHealthModify(): MutableLiveData<List<Health>> {

        val liveData = MutableLiveData<Health>()
//    val liveData = MutableLiveData<List<Health>>()



        FirebaseFirestore.getInstance()
            .collection(PATH_HEALTH)
//            .whereEqualTo("id","wmTB0Pb9ULegEiOWU9eA")
//            .orderBy("id", Query.Direction.DESCENDING)
            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, exception ->


                Logger.i("addSnapshotListener detect")

                exception?.let {
                    Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                }

                var list = Health()

//                val list = mutableListOf<Health>()
//                snapshot?.forEach { document ->
                for (document in snapshot!!) {
                    Logger.d(document.id + " => " + document.data)
                    val article = document.toObject(Health::class.java)
//                    list.add(article)
                    list = article
                }

                liveData.value = list
            }
        return liveData
    }


    override suspend fun addHealthDate(health: Health): Result<Boolean> = suspendCoroutine { continuation ->
        val remindDate = FirebaseFirestore.getInstance().collection(PATH_HEALTH)
        val document = remindDate.document()

        health.id = document.id
        health.createdTime = Calendar.getInstance().timeInMillis

        document
            .set(health)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Logger.i("health: $health")

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