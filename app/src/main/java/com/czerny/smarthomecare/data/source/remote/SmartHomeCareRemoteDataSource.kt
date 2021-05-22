package com.czerny.smarthomecare.data.source.remote


import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.*
import com.czerny.smarthomecare.data.source.SmartHomeCareDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.czerny.smarthomecare.util.Logger

object SmartHomeCareRemoteDataSource : SmartHomeCareDataSource {


    private const val PATH_HEALTH = "healthDate"
    private const val PATH_REMIND = "remindDate"
    private const val PATH_PROFILE = "profileDate"
    private const val KEY_CREATED_TIME = "createdTime"

    override suspend fun login(id: String): Result<Author> {
        TODO("Not yet implemented")
    }

    /** get health date*/
    override suspend fun getHealth(): Result<List<Health>> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_HEALTH)
//            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list = mutableListOf<Health>()
                    for (document in task.result!!) {
                        Logger.d(document.id + " => " + document.data)
                        val health = document.toObject(Health::class.java)
                        Log.i("czerny","document =${health}")
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
//            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
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
    }
    
    /** get health date*/

    /** get remind date*/
    override suspend fun getRemind(): Result<List<Remind>> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_REMIND)
//            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
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
//            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
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
    
    override suspend fun smartRemind(remind: Remind): Result<Boolean> = suspendCoroutine { continuation ->
        val remindDate = FirebaseFirestore.getInstance().collection(PATH_REMIND)
        val document = remindDate.document()

        remind.id = document.id
//        article.createdTime = Calendar.getInstance().timeInMillis

        document
            .set(remind)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Logger.i("remind: $remind")

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
    /** get remind date*/



    /**-----------------get profile date-----------------*/

/*    override suspend fun getProfile(user: User): Result<Boolean> = suspendCoroutine { continuation ->
        val users = FirebaseFirestore.getInstance().collection(PATH_PROFILE)
        val document = users.document()

        user.id = document.id
//        user.createdTime = Calendar.getInstance().timeInMillis

        document
            .set(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Logger.i("Publish: $user")

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
                        Log.i("czerny","datasource=${document.data}")

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



/*    override fun getLiveProfile(): MutableLiveData<User> {
//        override fun getLiveProfile(): MutableLiveData<List<User>> {
        val liveData = MutableLiveData<User>()
//        val liveData = MutableLiveData<List<User>>()
        FirebaseFirestore.getInstance()
            .collection(PATH_PROFILE)
//            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, exception ->

                Logger.i("addSnapshotListener detect")

                exception?.let {
                    Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                }


                val list = mutableListOf<User>()
                for (document in snapshot!!) {
                    Logger.d(document.id + " => " + document.data)
                    Log.i("czerny","=remote data source = ${document.data}")
                    val user = document.toObject(User::class.java) *//***//*
                    list.add(user)

                }

//                liveData.value = list
            }
        return liveData
    }

    override suspend fun smartProfile(user: User): Result<Boolean> = suspendCoroutine { continuation ->
        val userDate = FirebaseFirestore.getInstance().collection(PATH_PROFILE)
        val document = userDate.document()

        user.id = document.id
//        article.createdTime = Calendar.getInstance().timeInMillis

        document
            .set(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Logger.i("users: $user")

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


    /**add home data*/
/*    override suspend fun getHome(): Result<List<Remind>> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_REMIND)
//            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
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

    override fun getLiveHome(): MutableLiveData<List<Remind>> {

        val liveData = MutableLiveData<List<Remind>>()

        FirebaseFirestore.getInstance()
            .collection(PATH_REMIND)
//            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
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

    override suspend fun smartHome(remind: Remind): Result<Boolean> = suspendCoroutine { continuation ->
        val remindDate = FirebaseFirestore.getInstance().collection(PATH_REMIND)
        val document = remindDate.document()

        remind.id = document.id
//        article.createdTime = Calendar.getInstance().timeInMillis

        document
            .set(remind)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Logger.i("remind: $remind")

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
    /**add home data*/


}