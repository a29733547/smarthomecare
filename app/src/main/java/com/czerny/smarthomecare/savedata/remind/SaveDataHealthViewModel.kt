package com.czerny.smarthomecare.savedata.remind

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication

import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.network.LoadApiStatus
import com.czerny.smarthomecare.data.Result
import com.czerny.smarthomecare.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class SaveDataHealthViewModel(private val repository: SmartHomeCareRepository) : ViewModel(){

    var getFamily = ""



    private val _health = MutableLiveData<List<Health>>()
    val health: LiveData<List<Health>>
        get() = _health

    var liveHealth = MutableLiveData<List<Health>>()

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
    private val _navigateToHealthModify = MutableLiveData<Health>()
    val navigateToHealthModify: LiveData<Health>
        get() = _navigateToHealthModify

    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")

    }


    fun deleteHealth(health: Health, family: String) {

        coroutineScope.launch {

            _status.value = LoadApiStatus.LOADING
            when (val result = repository.deleteHealth(health, family)) {
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

    fun getHealthResult(family: String) {

        coroutineScope.launch {

            _status.value = LoadApiStatus.LOADING

            val result = repository.getHealth(family)
            Log.i("czerny save data", "save data =${result}")

            _health.value = when (result) {
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

    fun getLiveHealthResult(family: String) {
        liveHealth = repository.getLiveHealth(family)
        Log.i("czerny", "liveHealth = ${liveHealth}")
        _status.value = LoadApiStatus.DONE
        _refreshStatus.value = false
    }

    fun navigateToHealthModify(health: Health) {
        _navigateToHealthModify.value = health
    }

    fun onHealthModifylNavigated() {
        _navigateToHealthModify.value = null
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


