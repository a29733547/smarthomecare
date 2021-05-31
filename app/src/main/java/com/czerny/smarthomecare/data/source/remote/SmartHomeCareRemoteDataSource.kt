package com.czerny.smarthomecare.data.source.remote

//20210530 branch test

import android.content.ContentValues
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
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import java.util.*

object SmartHomeCareRemoteDataSource : SmartHomeCareDataSource {


    private const val PATH_HEALTH = "healthData"
    private const val PATH_REMIND = "remindData"
    private const val PATH_PROFILE = "profileDate"
    private const val KEY_CREATED_TIME = "createdTime"
    private const val PATH_CHATLIST = "chatList"

    override suspend fun login(id: String): Result<Author> {
        TODO("Not yet implemented")
    }


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
    }


    override suspend fun deleteRemind(remind: Remind): Result<Boolean> = suspendCoroutine { continuation ->

        val articles = FirebaseFirestore.getInstance().collection(PATH_REMIND)
        val document = articles.document(remind.id)
//        Log.i("czerny","articles.document = ${document}")

        remind.id = document.id

        document
            .delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Logger.i("Publish: $remind")

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


        override suspend fun remindModify(remind: Remind): Result<Boolean> = suspendCoroutine { continuation ->

        val remindDate = FirebaseFirestore.getInstance().collection(PATH_REMIND)
        val document = remindDate.document(remind.id)

        remind.id = document.id
        remind.createdTime = Calendar.getInstance().timeInMillis

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
//                        return@addOnCompleteListener
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

        val liveData = MutableLiveData<Health>()

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


                for (document in snapshot!!) {
                    Logger.d(document.id + " => " + document.data)
                    val article = document.toObject(Health::class.java)
                    list = article
                }

                liveData.value = list
            }
        return liveData
    }

    override fun getLiveRemindModify(): MutableLiveData<Remind> {

        val liveData = MutableLiveData<Remind>()

        FirebaseFirestore.getInstance()
            .collection(PATH_HEALTH)

            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, exception ->
                Logger.i("addSnapshotListener detect")
                exception?.let {
                    Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                }

                var list = Remind()


                for (document in snapshot!!) {
                    Logger.d(document.id + " => " + document.data)
                    val article = document.toObject(Remind::class.java)
                    list = article
                }

                liveData.value = list
            }
        return liveData
    }


    override suspend fun addHealthData(health: Health): Result<Boolean> = suspendCoroutine { continuation ->
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

    override suspend fun addRemindData(remind: Remind): Result<Boolean> = suspendCoroutine { continuation ->
        val remindDate = FirebaseFirestore.getInstance().collection(PATH_REMIND)
        val document = remindDate.document()

        remind.id = document.id
        remind.createdTime = Calendar.getInstance().timeInMillis

        document
            .set(remind)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Logger.i("health: $remind")

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

    override suspend fun postMessage(emails: List<String>, message: Message): Result<Boolean> = suspendCoroutine { continuation ->

        val chat = FirebaseFirestore.getInstance().collection(PATH_CHATLIST)
        chat.whereIn("attendees", listOf(emails, emails.reversed()))
            .get()
            .addOnSuccessListener { result ->
                val documentId = chat.document(result.documents[0].id)
                documentId
                    .update("latestTime", Calendar.getInstance().timeInMillis, "latestMessage", message.text)
            }
            .continueWithTask { task ->
                if (!task.isSuccessful) {
                    if (task.exception != null) {
                        task.exception?.let {
                            Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                            continuation.resume(Result.Error(it))
                        }
                    } else {
                        continuation.resume(Result.Fail(SmartHomeCareApplication.appContext.getString(R.string.you_shall_not_pass)))
                    }
                }

                task.result?.let {
                    val documentId2 = chat.document(it.documents[0].id).collection("message").document()

                    message.createdTime = Calendar.getInstance().timeInMillis
                    message.id = documentId2.id

                    chat.document(it.documents[0].id).collection("message").add(message)

                }


            }
            .addOnCompleteListener { taskTwo ->
                if (taskTwo.isSuccessful) {
                    Logger.i("Chatroom: $message")

                    continuation.resume(Result.Success(true))
                } else {
                    taskTwo.exception?.let {

                        Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                        continuation.resume(Result.Error(it))
                        return@addOnCompleteListener
                    }
                    continuation.resume(Result.Fail(SmartHomeCareApplication.appContext.getString(R.string.you_shall_not_pass)))
                }

            }

    }

    override fun getAllLiveMessage(emails: List<String>): MutableLiveData<List<Message>> {
        val liveData = MutableLiveData<List<Message>>()

        val chat = FirebaseFirestore.getInstance().collection("PATH_CHATLIST")
        chat.whereIn("attendees", listOf(emails, emails.reversed()))
            .get()
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                        return@addOnCompleteListener
                    }
                }

                task.result?.let {
                    chat.document(it.documents[0].id).collection("message")

                        .orderBy("createdTime", Query.Direction.ASCENDING)
                        .addSnapshotListener { snapshot, exception ->
                            Logger.i("add SnapshotListener detected")

                            exception?.let {
                                Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                            }

                            val list = mutableListOf<Message>()
                            snapshot?.forEach { document ->
                                Logger.d(document.id + " => " + document.data)

                                val message = document.toObject(Message::class.java)
                                list.add(message)
                            }
                            liveData.value = list

                        }

                }

            }
        return liveData


    }

}