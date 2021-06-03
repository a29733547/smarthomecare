package com.czerny.smarthomecare.data.source.remote

//20210530 branch test

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.*
import com.czerny.smarthomecare.data.source.SmartHomeCareDataSource
import com.czerny.smarthomecare.login.UserManager
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
    private const val PATH_FAMILY = "family"
    private const val PATH_USERS = "users"

    override suspend fun login(id: String): Result<Author> {
        TODO("Not yet implemented")
    }


    override suspend fun deleteHealth(health: Health): Result<Boolean> =
        suspendCoroutine { continuation ->

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
                        continuation.resume(
                            Result.Fail(
                                SmartHomeCareApplication.instance.getString(
                                    R.string.you_know_nothing
                                )
                            )
                        )
                    }
                }
        }


    override suspend fun deleteRemind(remind: Remind): Result<Boolean> =
        suspendCoroutine { continuation ->

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
                        continuation.resume(
                            Result.Fail(
                                SmartHomeCareApplication.instance.getString(
                                    R.string.you_know_nothing
                                )
                            )
                        )
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

    override suspend fun healthModify(health: Health): Result<Boolean> =
        suspendCoroutine { continuation ->
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
                        continuation.resume(
                            Result.Fail(
                                SmartHomeCareApplication.instance.getString(
                                    R.string.you_know_nothing
                                )
                            )
                        )
                    }
                }
        }


    override suspend fun remindModify(remind: Remind): Result<Boolean> =
        suspendCoroutine { continuation ->

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
                        continuation.resume(
                            Result.Fail(
                                SmartHomeCareApplication.instance.getString(
                                    R.string.you_know_nothing
                                )
                            )
                        )
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

    override suspend fun getHealthModify(id: String): Result<Health> =
        suspendCoroutine { continuation ->
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
                        continuation.resume(
                            Result.Fail(
                                SmartHomeCareApplication.instance.getString(
                                    R.string.you_know_nothing
                                )
                            )
                        )
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
                Log.i("czerny", "getLiveHealthModify = ${liveData.value}")
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


    override suspend fun addHealthData(health: Health): Result<Boolean> =
        suspendCoroutine { continuation ->
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
                        continuation.resume(
                            Result.Fail(
                                SmartHomeCareApplication.instance.getString(
                                    R.string.you_know_nothing
                                )
                            )
                        )
                    }
                }
        }

    override suspend fun addRemindData(remind: Remind): Result<Boolean> =
        suspendCoroutine { continuation ->
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
                        continuation.resume(
                            Result.Fail(
                                SmartHomeCareApplication.instance.getString(
                                    R.string.you_know_nothing
                                )
                            )
                        )
                    }
                }
        }

//    override suspend fun postMessage(chatRoom: ChatRoom): Result<Boolean> =
//        suspendCoroutine { continuation ->
//            val remindDate = FirebaseFirestore.getInstance()
//                .collection("family")
//            val document = remindDate
//                .document("NaNSjGX9Ltf7jTE0Ovji")
//                .collection("chatroom")
//                .document()
//
//            chatRoom.id = document.id
////            chatRoom.createdTime = Calendar.getInstance().timeInMillis
//
//            document
//                .set(chatRoom)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        Logger.i("health: $chatRoom")
//
//                        continuation.resume(Result.Success(true))
//                    } else {
//                        task.exception?.let {
//
//                            Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
//                            continuation.resume(Result.Error(it))
//                            return@addOnCompleteListener
//                        }
//                        continuation.resume(
//                            Result.Fail(
//                                SmartHomeCareApplication.instance.getString(
//                                    R.string.you_know_nothing
//                                )
//                            )
//                        )
//                    }
//                }
//        }

    override suspend fun postMessage(emails: List<String>, chatRoom: ChatRoom): Result<Boolean> =
        suspendCoroutine { continuation ->

            val chat = FirebaseFirestore.getInstance()
                .collection("family")


                    chat.document("NaNSjGX9Ltf7jTE0Ovji")
                        .collection("chatroom")

                .get()
                .addOnSuccessListener { result ->
                    val documentId = chat.document("NaNSjGX9Ltf7jTE0Ovji")
//                    val documentId = chat.document(result.documents[0].id)
                    documentId
                        .update(
                            "latestTime",
                            Calendar.getInstance().timeInMillis,
                            "latestMessage",
                            chatRoom.message
                        )
                }
                .continueWithTask { task ->
                    if (!task.isSuccessful) {
                        if (task.exception != null) {
                            task.exception?.let {
                                Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                                continuation.resume(Result.Error(it))
                            }
                        } else {
                            continuation.resume(
                                Result.Fail(
                                    SmartHomeCareApplication.appContext.getString(
                                        R.string.you_shall_not_pass
                                    )
                                )
                            )
                        }
                    }

                    task.result?.let {
                        val documentId2 =
//                            chat.document(it.documents[0].id).collection("chatroom").document()
                            chat.document("NaNSjGX9Ltf7jTE0Ovji").collection("chatroom").document()

                        chatRoom.createdTime = Calendar.getInstance().timeInMillis
                        chatRoom.id = documentId2.id

//                        chat.document(it.documents[0].id).collection("chatroom").add(chatRoom)
                        chat.document("NaNSjGX9Ltf7jTE0Ovji").collection("chatroom").add(chatRoom)


                    }


                }
                .addOnCompleteListener { taskTwo ->
                    if (taskTwo.isSuccessful) {
                        Logger.i("Chatroom: $chatRoom")

                        continuation.resume(Result.Success(true))
                    } else {
                        taskTwo.exception?.let {

                            Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                            continuation.resume(Result.Error(it))
                            return@addOnCompleteListener
                        }
                        continuation.resume(
                            Result.Fail(
                                SmartHomeCareApplication.appContext.getString(
                                    R.string.you_shall_not_pass
                                )
                            )
                        )
                    }

                }

        }

    override suspend fun postMessage(userId: String, message: String): Result<Boolean>
    = suspendCoroutine { continuation ->
        val ref = FirebaseFirestore.getInstance()
            .collection("family")
            .document("NaNSjGX9Ltf7jTE0Ovji")
            .collection("chatroom")

//            chatRoom.id = chat.id
//            chatRoom.createdTime = Calendar.getInstance().timeInMillis
        val userName = UserManager.user.userName

        val document = ref
            .document()

        val chat = ChatRoom(
            id = document.id,
            senderEmail = userId,
            senderName = userName,
            message = message,
            createdTime = Calendar.getInstance().timeInMillis
        )
        document
            .set(chat)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Logger.i("chat: $chat")

                    continuation.resume(Result.Success(true))
                } else {
                    task.exception?.let {

                        Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                        continuation.resume(Result.Error(it))
//                        return@addOnCompleteListener
                    }
                    continuation.resume(
                        Result.Fail(
                            SmartHomeCareApplication.instance.getString(
                                R.string.you_know_nothing
                            )
                        )
                    )
                }
            }
    }


    override fun getAllLiveMessage(emails: List<String>): MutableLiveData<List<ChatRoom>> {

        val liveData = MutableLiveData<List<ChatRoom>>()

        FirebaseFirestore.getInstance()
            .collection("family")
            .document("NaNSjGX9Ltf7jTE0Ovji")
            .collection("chatroom")
            .orderBy(KEY_CREATED_TIME, Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, exception ->

                Logger.i("addSnapshotListener detect")

                exception?.let {
                    Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                }

                val list = mutableListOf<ChatRoom>()
                for (document in snapshot!!) {
                    Logger.d(document.id + " => " + document.data)
                    val article = document.toObject(ChatRoom::class.java)
                    list.add(article)
                    Log.i("czerny", "list.add(article) = ${article}")
                }

                liveData.value = list

            }
        return liveData
    }

//    override fun getAllLiveMessage(emails: List<String>): MutableLiveData<List<ChatRoom>> {
////        override fun getAllLiveMessage(id: String): MutableLiveData<List<ChatRoom>> {
//        val liveData = MutableLiveData<List<ChatRoom>>()
//
//        val chat = FirebaseFirestore.getInstance()
//            .collection("family")
////        chat.whereIn("attendees", listOf(emails, emails.reversed()))
//            chat.document(
//                    "NaNSjGX9Ltf7jTE0Ovji").collection("chatroom")
//            .get()
//            .addOnCompleteListener { task ->
//                if (!task.isSuccessful) {
//                    task.exception?.let {
//                        Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
//                        return@addOnCompleteListener
//                    }
//                }
//
//                task.result?.let {
////                    chat.document(it.documents[0].id).collection("chatroom")
//                    chat.document("NaNSjGX9Ltf7jTE0Ovji")
//                        .collection("chatroom")
//                        .orderBy(KEY_CREATED_TIME, Query.Direction.ASCENDING)
//                        .addSnapshotListener { snapshot, exception ->
//                            Logger.i("add SnapshotListener detected")
//
//                            exception?.let {
//                                Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
//                            }
//
//                            val list = mutableListOf<ChatRoom>()
//                            snapshot?.forEach { document ->
//                                Logger.d(document.id + " => " + document.data)
//
//                                val message = document.toObject(ChatRoom::class.java)
//                                list.add(message)
//                            }
//                            liveData.value = list
//                            Log.i("czerny", "liveData.value =${list}")
//
//                        }
//
//                }
//
//            }
//        return liveData
//    }

    override suspend fun getUser(userEmail: String): Result<User> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_USERS)
            .whereEqualTo("email", userEmail)
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

}