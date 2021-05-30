package com.czerny.smarthomecare.savedata.modify

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.data.Result
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.network.LoadApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SaveDataRemindModifyViewModel (private val repository: SmartHomeCareRepository,
                                     private val arguments: Remind): ViewModel() {

    private var _remindModify = MutableLiveData<Remind>().apply {
        value = arguments
    }
    val remindModify: LiveData<Remind>
        get() = _remindModify

//    private var _remindModify = MutableLiveData<Remind>()
//    val remindModify: LiveData<Remind>
//        get() = _remindModify


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

//    val name = MutableLiveData<String>()
//    val date = MutableLiveData<String>()
//    val hours = MutableLiveData<String>()
//    val minute = MutableLiveData<String>()
//    val title = MutableLiveData<String>()
//    val content = MutableLiveData<String>()


//
//    init {
//        Logger.i("------------------------------------")
//        Logger.i("[${this::class.simpleName}]${this}")
//        Logger.i("------------------------------------")
//
//        getLiveRemindModify()
//
//    }
//
//
//



    fun healthRemindFun() {

        val remind = remindModify.value?.let {
            Remind(
                id = it.id,
                name = it.name,
                hours = it.hours,
                minute = it.minute,
                date = it.date,
                content = it.content,
                title = it.title,
            )
        }

//    val remind = Remind(
//
//        id = remindModify.value?.id,
//        name = remindModify.value?.name,
//        hours = remindModify.value?.hours,
//        minute = remindModify.value?.minute,
//        date = remindModify.value?.date,
//        content = remindModify.value?.content,
//        title = remindModify.value?.title,
//    )

        coroutineScope.launch {

            _status.value = LoadApiStatus.LOADING

            when (val result = remind?.let { repository.remindModify(it) }) {
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
//
    fun leave(needRefresh: Boolean = false) {
        _leave.value = needRefresh
    }
//
//
//    fun getLiveRemindModify() {
//        _remindModify = repository.getLiveRemindModify()
//        _status.value = LoadApiStatus.DONE
//        _refreshStatus.value = false
//    }

}