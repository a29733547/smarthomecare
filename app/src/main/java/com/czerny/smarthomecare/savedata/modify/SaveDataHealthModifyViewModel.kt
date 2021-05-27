package com.czerny.smarthomecare.savedata.modify


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Result
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.login.UserManager
import com.czerny.smarthomecare.network.LoadApiStatus
import com.czerny.smarthomecare.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SaveDataHealthModifyViewModel (private val repository: SmartHomeCareRepository,) :  ViewModel() {



    private var _healthModify = MutableLiveData<Health>()
    val healthModify: LiveData<Health>
        get() = _healthModify


    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main + viewModelJob)

    private val _status = MutableLiveData<LoadApiStatus>()
    val status: LiveData<LoadApiStatus>
        get() = _status

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _refreshStatus = MutableLiveData<Boolean>()
    val refreshStatus: LiveData<Boolean>
        get() = _refreshStatus

    private val _leave = MutableLiveData<Boolean>()

//
//    var health = Health(
//        id = "",
//        healthPlaceData = "",
//        title = "",
//        name = "",
//        content = "",
//        note = ""
//    )


    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")

        getLiveHealthModify()

}





//    fun getHealthModify(id: String) {
//
//        coroutineScope.launch {
//
//            _status.value = LoadApiStatus.LOADING
//
//            val result = repository.getHealthModify(id)
//
//            _healthModify.value = when (result) {
//                is Result.Success -> {
//                    _error.value = null
//                    _status.value = LoadApiStatus.DONE
//                    result.data
//                }
//                is Result.Fail -> {
//                    _error.value = result.error
//                    _status.value = LoadApiStatus.ERROR
//                    null
//                }
//                is Result.Error -> {
//                    _error.value = result.exception.toString()
//                    _status.value = LoadApiStatus.ERROR
//                    null
//                }
//                else -> {
//                    _error.value =
//                        SmartHomeCareApplication.instance.getString(R.string.you_know_nothing)
//                    _status.value = LoadApiStatus.ERROR
//                    null
//                }
//            }
//            _refreshStatus.value = false
//        }
//    }

    fun healthModifyFun(health: Health) {

        coroutineScope.launch {

            _status.value = LoadApiStatus.LOADING

            when (val result = repository.healthModify(health)) {
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

    fun leave(needRefresh: Boolean = false) {
        _leave.value = needRefresh
    }


    fun getLiveHealthModify() {
        _healthModify = repository.getLiveHealthModify()
        _status.value = LoadApiStatus.DONE
        _refreshStatus.value = false
    }

}