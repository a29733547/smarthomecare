package com.czerny.smarthomecare.addremind.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Result
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.network.LoadApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch



class AddHealthEditViewModel(private val repository: SmartHomeCareRepository) : ViewModel() {

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main + viewModelJob)

    private val _addHealthData = MutableLiveData<Health>()
    val addHealthData: LiveData<Health>
        get() = _addHealthData


//    val id = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val healthPlaceData = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val note = MutableLiveData<String>()



    private val _status = MutableLiveData<LoadApiStatus>()
    val status: LiveData<LoadApiStatus>
        get() = _status

    private val _leave = MutableLiveData<Boolean>()
    val leave: LiveData<Boolean>
        get() = _leave


    private val _error = MutableLiveData<String>()

    fun leave(needRefresh: Boolean = false) {
        _leave.value = needRefresh
    }

    fun addHealthDataFun() {

        val health = Health(
            healthPlaceData = healthPlaceData.value!!,
            title = title.value!!,
            name = name.value!!,
            content = content.value!!,
            note = note.value!!
        )

        coroutineScope.launch {

            _status.value = LoadApiStatus.LOADING

            when (val result = repository.addHealthData(health)) {
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

