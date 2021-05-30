package com.czerny.smarthomecare.addremind.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.data.Result
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.network.LoadApiStatus
import com.czerny.smarthomecare.util.Logger

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddRemindEditViewModel (private val repository: SmartHomeCareRepository): ViewModel(){



    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _remindData = MutableLiveData<Remind>()
    val remindData: LiveData<Remind>
        get() = _remindData


    val id = MutableLiveData<String>()
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")
    }

    fun addRemindData() {
//        val remind = remindData.value?.let {
//            Remind(
//                id = it.id,
//                name = it.name,
//                hours = it.hours,
//                minute = it.minute,
//                date = it.date,
//                content = it.content,
//                title = it.title,
//            )
//        }

        val remind = Remind(
            name = name.value,
            hours = hours.value,
            minute = minute.value,
            date = date.value,
            content = content.value,
            title = title.value,
        )

        coroutineScope.launch {

//            val result = repository.addRemindData(remind)
            _status.value = LoadApiStatus.LOADING

            when (val result = repository.addRemindData(remind)){
//            when (val result = remind?.let { repository.addRemindData(it) }){
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
