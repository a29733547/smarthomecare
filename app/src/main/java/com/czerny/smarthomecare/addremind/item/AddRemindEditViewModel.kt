package com.czerny.smarthomecare.addremind.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.data.Result
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.network.LoadApiStatus

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddRemindEditViewModel (private val repository: SmartHomeCareRepository): ViewModel(){



    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)



    //    val id = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val hours = MutableLiveData<String>()
    val minute = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()



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

    fun addRemindData() {
        val remind = Remind(

//            name = name,
//            hours = "",
//            minute = "",
//            date = "",
//            content = "",
//            title = "",

            name = name.value!!,
            hours = hours.value!!,
            minute = minute.value!!,
            date = date.value!!,
            content = content.value!!,
            title = title.value!!,
        )

        coroutineScope.launch {

            _status.value = LoadApiStatus.LOADING

            when (val result = repository.addRemindData(remind)) {
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
