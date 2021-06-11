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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import java.util.*

object SmartHomeCareRemoteDataSource : SmartHomeCareDataSource {

    //2021 0607 branch test

    private const val PATH_FAMILY_INFO = "familyInfo"
    private const val PATH_USER_INFO = "userInfo"
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


//    override suspend fun postMessage(emails: List<String>, chatRoom: ChatRoom, family: String): Result<Boolean> =
//        suspendCoroutine { continuation ->
//
//            val chat = FirebaseFirestore.getInstance()
//                .collection("family")
//
//
//                    chat.document("NaNSjGX9Ltf7jTE0Ovji")
//                        .collection("chatroom")
//
//                .get()
//                .addOnSuccessListener { result ->
//                    val documentId = chat.document("NaNSjGX9Ltf7jTE0Ovji")
////                    val documentId = chat.document(result.documents[0].id)
//                    documentId
//                        .update(
//                            "latestTime",
//                            Calendar.getInstance().timeInMillis,
//                            "latestMessage",
//                            chatRoom.message
//                        )
//                }
//                .continueWithTask { task ->
//                    if (!task.isSuccessful) {
//                        if (task.exception != null) {
//                            task.exception?.let {
//                                Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
//                                continuation.resume(Result.Error(it))
//                            }
//                        } else {
//                            continuation.resume(
//                                Result.Fail(
//                                    SmartHomeCareApplication.appContext.getString(
//                                        R.string.you_shall_not_pass
//                                    )
//                                )
//                            )
//                        }
//                    }
//
//                    task.result?.let {
//                        val documentId2 =
////                            chat.document(it.documents[0].id).collection("chatroom").document()
//                            chat.document("NaNSjGX9Ltf7jTE0Ovji").collection("chatroom").document()
//
//                        chatRoom.createdTime = Calendar.getInstance().timeInMillis
//                        chatRoom.id = documentId2.id
//
////                        chat.document(it.documents[0].id).collection("chatroom").add(chatRoom)
//                        chat.document("NaNSjGX9Ltf7jTE0Ovji").collection("chatroom").add(chatRoom)
//
//
//                    }
//
//
//                }
//                .addOnCompleteListener { taskTwo ->
//                    if (taskTwo.isSuccessful) {
//                        Logger.i("Chatroom: $chatRoom")
//
//                        continuation.resume(Result.Success(true))
//                    } else {
//                        taskTwo.exception?.let {
//
//                            Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
//                            continuation.resume(Result.Error(it))
//                            return@addOnCompleteListener
//                        }
//                        continuation.resume(
//                            Result.Fail(
//                                SmartHomeCareApplication.appContext.getString(
//                                    R.string.you_shall_not_pass
//                                )
//                            )
//                        )
//                    }
//
//                }
//        }





    /**------------------*/

    override suspend fun postMessage(userId: String, message: String, family: String): Result<Boolean>
    = suspendCoroutine { continuation ->

        val ref = FirebaseFirestore.getInstance()
            .collection("family")
            .document(family)
//            .document("NaNSjGX9Ltf7jTE0Ovji")
            .collection("chatroom")

//            chatRoom.id = chat.id
//            chatRoom.createdTime = Calendar.getInstance().timeInMillis
//        val userName = UserManager.user.userName
        val userName = UserManager.user.name

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


    override fun getAllLiveMessage(emails: List<String>, family: String): MutableLiveData<List<ChatRoom>> {

        val liveData = MutableLiveData<List<ChatRoom>>()

//       val chat = FirebaseFirestore.getInstance()
//            .collection("family")
//        chat.whereIn("attendees", listOf(emails, emails.reversed()))
//            chat.document(emails[0])

        FirebaseFirestore.getInstance()
            .collection("family")
            .document(family)
//            .document("NaNSjGX9Ltf7jTE0Ovji")


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

    /**------------------*/

//    override fun getAllLiveMessage(emails: List<String>): MutableLiveData<List<ChatRoom>> {
//
//        val liveData = MutableLiveData<List<ChatRoom>>()
//
//        val chat = FirebaseFirestore.getInstance().collection("family")
//        chat.whereIn("attendees", listOf(emails, emails.reversed()))
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
//                    chat.document("NaNSjGX9Ltf7jTE0Ovji")
////                    chat.document(it.documents[0].id)
//                        .collection("chatroom")
//                        .orderBy(KEY_CREATED_TIME)
//                        .addSnapshotListener { snapshot, exception ->
//                            Logger.i("addSnapshotListener detect")
//
//                            if (snapshot != null) {
//                                Log.d("outbounder", "snapshot = ${snapshot.documents}")
//                            } else {
//                                Log.d("outbounder", "snapshot = null")
//                            }
//
//
//                            exception?.let {
//                                Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
//                            }
//
//                            val list = mutableListOf<ChatRoom>()
//
//                            for (document in snapshot!!) {
//                                Logger.d(document.id + " => " + document.data)
//                                Log.d("outbounder", "${document.data}")
//                                val message = document.toObject(ChatRoom::class.java)
//                                list.add(message)
//                            }
//
//                            liveData.value = list
//                            Logger.w("liveData.value = ${liveData.value}")
//                        }
//                }
//            }
//        return liveData
//    }




    override suspend fun firebaseAuthWithGoogle(idToken: String): Result<FirebaseUser> = suspendCoroutine { continuation ->
        Log.d("check_googleSign", "firebaseAuthWithGoogle in DataSource is used.")
        Log.d("check_googleSign", "idToken = $idToken")
        val auth = Firebase.auth
//        val auth = FirebaseAuth.getInstance()

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(LoginActivity.TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    user?.let {
                        continuation.resume(Result.Success(it))
                    }
                } else {
                    // If sign in fails, display a message to the user.
//                    Log.w(LoginActivity.TAG, "signInWithCredential:failure", task.exception)
                    task.exception?.let {
                        continuation.resume(Result.Error(it))
                        return@addOnCompleteListener
                    }
                    continuation.resume(Result.Fail(SmartHomeCareApplication.instance.getString(R.string.you_know_nothing)))
                }
            }
    }

    override suspend fun getUser(): Result<User> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
                        .collection("user")
//            .whereEqualTo("email", userEmail)
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

    override suspend fun postUser(user: User): Result<Boolean> = suspendCoroutine { continuation ->
        val db = FirebaseFirestore.getInstance().collection("user")
        val document = db.document(user.id)
        Log.d("checkUser", "user in DataSource = $user")
        Log.d("checkUser", "user.id in DataSource = ${user.id}")
        Log.d("checkUser", "user.email in DataSource = ${user.email}")

        db.whereEqualTo("email", user.email)
            .get()
            .addOnSuccessListener {  documents ->
                Log.d("documents"," Already initialized")
                Log.d("documents", "documents = ${documents.isEmpty}}")

                for (document in documents) {
                    Log.d("documents", "Received in DataSource = ${document.id} => ${document.data}")
                }
                if (documents.isEmpty) {
                    document
                        .set(user)
                        .addOnSuccessListener {
                            Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                            Log.d("checkUser", "User in addOnSuccessListener = $user")

                            Logger.i("User: $user")
//                            continuation.resume(Result.Success(true))
                        }
                        .addOnFailureListener { e ->
                            Log.w(ContentValues.TAG, "Error writing document", e)
                            Logger.w("[${this::class.simpleName}] Error getting documents. ${e.message}")
//                            continuation.resume(Result.Error(e))
                        }
//                    continuation.resume(Result.Fail(KnowHowBindingApplication.instance.getString(R.string.you_know_nothing)))
                }
            }
            .addOnFailureListener { exception ->
                Log.w("documents", "Error getting documents: ", exception)
            }
    }


    override fun getUserList(): MutableLiveData<List<UserInfo>> {

        val liveData = MutableLiveData<List<UserInfo>>()

        FirebaseFirestore.getInstance()
            .collection(PATH_USER_INFO)
//            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, exception ->

                Logger.i("addSnapshotListener detect")

                exception?.let {
                    Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                }

                val list = mutableListOf<UserInfo>()
                for (document in snapshot!!) {
                    Logger.d(document.id + " => " + document.data)
                    val article = document.toObject(UserInfo::class.java)
                    list.add(article)
                    Log.i("czerny", "list.add(article) = ${article}")
                }

                liveData.value = list
                Log.i("getUserList", "liveData = ${liveData.value}")

            }
        return liveData
    }

//    override fun getFamilyList(): MutableLiveData<FamilyInfo> {
    override fun getFamilyList(): MutableLiveData<List<FamilyInfo>> {

        val liveData = MutableLiveData<List<FamilyInfo>>()
//        val liveData = MutableLiveData<FamilyInfo>()

        FirebaseFirestore.getInstance()
            .collection(PATH_FAMILY_INFO)
//            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, exception ->

                Logger.i("addSnapshotListener detect")

                exception?.let {
                    Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                }

//                var list = FamilyInfo()
                val list = mutableListOf<FamilyInfo>()
                for (document in snapshot!!) {
                    Logger.d(document.id + " => " + document.data)
                    val article = document.toObject(FamilyInfo::class.java)
                    list.add(article)
//                    list = article
                    Log.i("czerny", "list.add(article) = ${article}")
                }


                liveData.value = list
                Log.i("getUserList", "liveData = ${liveData.value}")

            }
        return liveData
    }

    override suspend fun pushFamily(user: User?): Result<Boolean>
            = suspendCoroutine { continuation ->

        val ref = FirebaseFirestore.getInstance()
            .collection("family")
//            .document()
//            .collection("chatroom")

        if (user != null) {
            user.createdTime = Calendar.getInstance().timeInMillis
        }


        val document = ref
            .document(user!!.familyName)

        user.familyId = document.id

        document
                .set(user)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Logger.i("chat: $user")

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

}