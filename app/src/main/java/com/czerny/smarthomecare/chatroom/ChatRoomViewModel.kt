package com.czerny.smarthomecare.chatroom

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.data.Result
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.ChatRoom
import com.czerny.smarthomecare.data.User
import com.czerny.smarthomecare.data.UserInfo
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.login.UserManager
import com.czerny.smarthomecare.network.LoadApiStatus
import com.czerny.smarthomecare.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ChatRoomViewModel(private val repository: SmartHomeCareRepository) : ViewModel() {

//    val currentChattingUser = userEmail

    val userEmail = "a29733547@gmail.com"




    val enterMessage = MutableLiveData<String>()

    var allLiveMessage = MutableLiveData<List<ChatRoom>>()


    private val _leave = MutableLiveData<Boolean>()

    val leave: LiveData<Boolean>
        get() = _leave

    // status: The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<LoadApiStatus>()

    val status: LiveData<LoadApiStatus>
        get() = _status

    // error: The internal MutableLiveData that stores the error of the most recent request
    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")

//        getAllLiveMessage(getUserEmails(UserManager.user.userId, userEmail))
        getAllLiveMessage(getUserEmails(UserManager.user.id, userEmail))
//        Log.i("czerny","getEmail1 = ${UserManager.user.userId}")
        Log.i("czerny","getEmail1 = ${UserManager.user.id}")
        Log.i("czerny","getEmail2 = ${userEmail}")
    }

    fun sendMessage(userId: String) {
        postMessage(userId, enterMessage.value.toString())
    }

    fun getUserEmails(user1Email: String, user2Email: String): List<String> {
        return listOf(user1Email, user2Email)
    }

    fun postMessage(userId: String, message: String) {

        coroutineScope.launch {

            _status.value = LoadApiStatus.LOADING

            when (val result = repository.postMessage(userId, message)) {
                is Result.Success -> {
                    _error.value = null
                    _status.value = LoadApiStatus.DONE
                    leave(true)
                }
                is Result.Fail -> {
                    _error.value = result.error
                    _status.value = LoadApiStatus.ERROR
                }
                is Result.Error -> {
                    _error.value = result.exception.toString()
                    _status.value = LoadApiStatus.ERROR
                }
                else -> {
                    _error.value = SmartHomeCareApplication.instance.getString(R.string.you_shall_not_pass)
                    _status.value = LoadApiStatus.ERROR
                }
            }
        }

    }

    private fun getAllLiveMessage(userEmails: List<String>) {

        allLiveMessage = repository.getAllLiveMessage(userEmails)
        _status.value = LoadApiStatus.DONE
    }

    fun leave(needRefresh: Boolean = false) {
        _leave.value = needRefresh
    }

}