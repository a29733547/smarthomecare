package com.czerny.smarthomecare.savedata.remind

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.data.Result
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.network.LoadApiStatus
import com.czerny.smarthomecare.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SaveDataRemindViewModel (private val repository: SmartHomeCareRepository): ViewModel(){


    private val _remind = MutableLiveData<List<Remind>>()
    val remind: LiveData<List<Remind>>
        get() = _remind

    var liveRemind = MutableLiveData<List<Remind>>()

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

    // Handle navigation to Savedata Health Modify
    private val _navigateToRemindModify = MutableLiveData<Remind>()
    val navigateToRemindModify: LiveData<Remind>
        get() = _navigateToRemindModify

    fun onRemindModifylNavigated() {
        _navigateToRemindModify.value = null
    }


    /**
     * Call getRemindResult() on init so we can display status immediately.
     */
    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")

        if (SmartHomeCareApplication.instance.isLiveDataDesign()) {
            getLiveRemindResult()
        } else {
            getRemindResult()

        }
    }

    fun deleteRemind(remind: Remind) {
//
//        if (_author.value == null) {
//            _error.value = "who r u?"
//            _refreshStatus.value = false
//            return
//        }

        coroutineScope.launch {

            _status.value = LoadApiStatus.LOADING
            when (val result = repository.deleteRemind(remind)) {
                is Result.Success -> {
                    _error.value = null
                    _status.value = LoadApiStatus.DONE
                    refresh()
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
            _refreshStatus.value = false
        }
    }

    fun getRemindResult() {

        coroutineScope.launch {

            _status.value = LoadApiStatus.LOADING

            val result = repository.getRemind()

            _remind.value = when (result) {
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
                    _error.value = SmartHomeCareApplication.instance.getString(R.string.you_know_nothing)
                    _status.value = LoadApiStatus.ERROR
                    null
                }
            }
            _refreshStatus.value = false
        }
    }

    fun getLiveRemindResult() {
        liveRemind = repository.getLiveRemind()
        _status.value = LoadApiStatus.DONE
        _refreshStatus.value = false
    }

    fun navigateToRemindModify(remind: Remind) {
        _navigateToRemindModify.value = remind
    }


    fun refresh() {

        if (SmartHomeCareApplication.instance.isLiveDataDesign()) {
            _status.value = LoadApiStatus.DONE
            _refreshStatus.value = false

        } else {
            if (status.value != LoadApiStatus.LOADING) {
                SmartHomeCareApplication()
            }
        }
    }

}