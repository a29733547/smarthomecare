package com.czerny.smarthomecare.userfamily

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.*
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.network.LoadApiStatus
import com.czerny.smarthomecare.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserFamilyViewModel (private val repository: SmartHomeCareRepository) : ViewModel() {

    private var _profile = MutableLiveData<User>()
    val profile: LiveData<User>
        get() = _profile


    var liveUserInfo = MutableLiveData<List<UserInfo>>()

    var liveFamilyInfo = MutableLiveData<List<FamilyInfo>>()
//    var liveFamilyInfo = MutableLiveData<FamilyInfo>()

    val familyName = MutableLiveData<String>()

//    private var _userInfo = MutableLiveData<List<UserInfo>>()
//    val userInfo: LiveData<List<UserInfo>>
//        get() = _userInfo

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main + viewModelJob)

    // status: The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<LoadApiStatus>()
    val status: LiveData<LoadApiStatus>
        get() = _status

    // error: The internal MutableLiveData that stores the error of the most recent request
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    // status for the loading icon of swl
    private val _refreshStatus = MutableLiveData<Boolean>()
    val refreshStatus: LiveData<Boolean>
        get() = _refreshStatus

    private val _leave = MutableLiveData<Boolean>()
    val leave: LiveData<Boolean>
        get() = _leave

    fun leave(needRefresh: Boolean = false) {
        _leave.value = needRefresh
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")

        getProfileResult()
//        getLiveUserInfoResult()
        getLiveFamilyInfoResult()

    }

    fun getLiveUserInfoResult() {
        liveUserInfo = repository.getUserList()
        Log.i("getLiveUserInfoResult" , "liveUserInfo = ${liveUserInfo}")
        _status.value = LoadApiStatus.DONE
        _refreshStatus.value = false
    }

    fun getLiveFamilyInfoResult() {
        liveFamilyInfo = repository.getFamilyList()
        Log.i("getLiveFamilyInfoResult" , " liveFamilyInfo = ${liveUserInfo}")
        _status.value = LoadApiStatus.DONE
        _refreshStatus.value = false
    }


    fun getProfileResult() {

        coroutineScope.launch {

            _status.value = LoadApiStatus.LOADING

            val result = repository.getUser()

            _profile.value = when (result) {
                is Result.Success -> {
                    _error.value = null
                    _status.value = LoadApiStatus.DONE
                    result.data
                }
                is Result.Fail -> {
                    _error.value = result.error
                    _status.value = LoadApiStatus.ERROR
                    null
                }
                is Result.Error -> {
                    _error.value = result.exception.toString()
                    _status.value = LoadApiStatus.ERROR
                    null
                }
                else -> {
                    _error.value =
                        SmartHomeCareApplication.instance.getString(R.string.you_know_nothing)
                    _status.value = LoadApiStatus.ERROR
                    null
                }
            }
            _refreshStatus.value = false
        }
    }

    fun addFamily() {

        val user = profile.value?.let {
            User(
                familyId = it.familyId,
                familyName = it.familyName
            )
        }


        coroutineScope.launch {

            _status.value = LoadApiStatus.LOADING

            when (val result = repository.pushFamily(user)){
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
                    _error.value = SmartHomeCareApplication.instance.getString(R.string.you_know_nothing)
                    _status.value = LoadApiStatus.ERROR
                }
            }
        }
    }

}