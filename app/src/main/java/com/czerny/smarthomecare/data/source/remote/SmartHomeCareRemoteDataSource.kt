package com.czerny.smarthomecare.data.source.remote

//20210530 branch test

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.*
import com.czerny.smarthomecare.data.source.SmartHomeCareDataSource
import com.czerny.smarthomecare.login.FamilyManger
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
    private const val PATH_HEALTH = "healthData"
    private const val PATH_REMIND = "remindData"
    private const val PATH_PROFILE = "profileDate"
    private const val KEY_CREATED_TIME = "createdTime"
    private const val PATH_FAMILY = "family"
    private const val PATH_CHATROOM = "chatroom"






    override suspend fun deleteHealth(health: Health, family: String): Result<Boolean> =
        suspendCoroutine { continuation ->

            val articles = FirebaseFirestore.getInstance()
                .collection(PATH_FAMILY)
                .document(family)
                .collection(PATH_HEALTH)
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


    override suspend fun deleteRemind(remind: Remind, family: String  ): Result<Boolean> =
        suspendCoroutine { continuation ->

            val articles = FirebaseFirestore.getInstance()
                .collection(PATH_FAMILY)
                .document(family)
                .collection(PATH_REMIND)
            val document = articles.document(remind.id)

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
    override suspend fun getHealth(family: String): Result<List<Health>> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_FAMILY)
            .document(family)
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

    override fun getLiveHealth(family: String): MutableLiveData<List<Health>> {

        val liveData = MutableLiveData<List<Health>>()

        FirebaseFirestore.getInstance()
            .collection(PATH_FAMILY)
            .document(family)
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


    /** -----------------------get remind date---------------------------*/
    override suspend fun getRemind(family: String): Result<List<Remind>> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_FAMILY)
            .document(family)
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

    override fun getLiveRemind(family: String): MutableLiveData<List<Remind>> {

        val liveData = MutableLiveData<List<Remind>>()

        FirebaseFirestore.getInstance()
            .collection(PATH_FAMILY)
            .document(family)
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
    /** -----------------------get remind date---------------------------*/


    override suspend fun healthModify(health: Health, family: String): Result<Boolean> =
        suspendCoroutine { continuation ->
            val remindDate = FirebaseFirestore.getInstance().collection(PATH_FAMILY)
            val document = remindDate.document(family).collection(PATH_HEALTH).document(health.id)

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


    override suspend fun remindModify(remind: Remind, family: String  ): Result<Boolean> =
        suspendCoroutine { continuation ->

            val remindDate = FirebaseFirestore.getInstance().collection(PATH_FAMILY)
            val document = remindDate.document(family).collection(PATH_REMIND).document(remind.id)

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

    override suspend fun getProfile(): Result<Profile> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_PROFILE)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var list = Profile()

                    for (document in task.result!!) {
                        Logger.d(document.id + " => " + document.data)


                        val profile = document.toObject(Profile::class.java)
                        list = profile

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

    override suspend fun addHealthData(health: Health, family: String): Result<Boolean> =
        suspendCoroutine { continuation ->
            val remindDate = FirebaseFirestore.getInstance().collection(PATH_FAMILY)
            val document = remindDate.document(family).collection(PATH_HEALTH).document()

            health.familyName = family

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

    override suspend fun addRemindData(remind: Remind, family: String): Result<Boolean> =
        suspendCoroutine { continuation ->
            val remindDate = FirebaseFirestore.getInstance().collection(PATH_FAMILY)
            val document = remindDate.document(family).collection(PATH_REMIND).document()


            remind.familyName = family

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


    /**------------------*/

    override suspend fun postMessage(userId: String, message: String, family: String): Result<Boolean>
    = suspendCoroutine { continuation ->

        val ref = FirebaseFirestore.getInstance()
            .collection(PATH_FAMILY)
            .document(family)
            .collection(PATH_CHATROOM)
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


        FirebaseFirestore.getInstance()
            .collection(PATH_FAMILY)
            .document(family)
            .collection(PATH_CHATROOM)
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

//




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

        val name = UserManager.user.name
        
        FirebaseFirestore.getInstance()
            .collection("user")
            .whereEqualTo("name", name)
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


    override fun getFamilyList(): MutableLiveData<List<FamilyInfo>> {

        val liveData = MutableLiveData<List<FamilyInfo>>()
        FirebaseFirestore.getInstance()
            .collection(PATH_FAMILY_INFO)
            .addSnapshotListener { snapshot, exception ->

                Logger.i("addSnapshotListener detect")

                exception?.let {
                    Logger.w("[${this::class.simpleName}] Error getting documents. ${it.message}")
                }


                val list = mutableListOf<FamilyInfo>()
                for (document in snapshot!!) {
                    Logger.d(document.id + " => " + document.data)
                    val article = document.toObject(FamilyInfo::class.java)
                    list.add(article)
                    Log.i("czerny", "list.add(article) = ${article}")
                }
                liveData.value = list
            }
        return liveData
    }

    override suspend fun pushFamily(user: User?): Result<Boolean>
            = suspendCoroutine { continuation ->

        val ref = FirebaseFirestore.getInstance()
            .collection(PATH_FAMILY_INFO)
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