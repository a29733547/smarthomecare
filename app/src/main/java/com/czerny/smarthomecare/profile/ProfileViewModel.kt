package com.czerny.smarthomecare.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Result
import com.czerny.smarthomecare.data.User
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.network.LoadApiStatus
import com.czerny.smarthomecare.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: SmartHomeCareRepository) : ViewModel() {

//    沒有好像也可以？
  /*  val profileId = MutableLiveData<String>()
    val profileNameData = MutableLiveData<String>()
    val profileBirth = MutableLiveData<String>()
    val profilYear = MutableLiveData<String>()
    val profileWeight = MutableLiveData<String>()
    val profileBlood = MutableLiveData<String>()
    val profileGenetic = MutableLiveData<String>()
    val profileAllergy = MutableLiveData<String>()
    val profileNote = MutableLiveData<String>()
    val profileFamilyId = MutableLiveData<Int>()

    val usersProfile = User(
        profileId.value ?: "",
        profileNameData.value ?: "",
        profileBirth.value ?: "",
        profilYear.value ?: "",
        profileWeight.value ?: "",
        profileBlood.value ?: "",
        profileGenetic.value ?: "",
        profileAllergy.value ?: "",
        profileNote.value ?: "",
        profileFamilyId.value ?: 0,
    )*/

    private val _profile = MutableLiveData<User>()
    val profile: LiveData<User>
        get() = _profile


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



    /**
     * Call getRemindResult() on init so we can display status immediately.
     */
    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")
        getProfileResult()

    }

    fun getProfileResult() {

        coroutineScope.launch {

            _status.value = LoadApiStatus.LOADING

            val result = repository.getProfile()

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

}