package com.czerny.smarthomecare.savedata.remind

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.network.LoadApiStatus
import com.czerny.smarthomecare.data.Result
import com.czerny.smarthomecare.data.source.remote.SmartHomeCareRemoteDataSource
import com.czerny.smarthomecare.util.Logger
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

//class SaveDataHealthViewModel : ViewModel(){
class SaveDataHealthViewModel(private val repository: SmartHomeCareRepository) : ViewModel(){



//    private lateinit var repository: SmartHomeCareRepository

    var editableList: MutableList<Health> = mutableListOf()

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






//    val gethealthId = MutableLiveData<String>()
//    val gethealthTitle = MutableLiveData<String>()
//    val gethealthPlace = MutableLiveData<String>()
//    val gethealthContent = MutableLiveData<String>()
//    val gethealthName = MutableLiveData<String>()
//    val gethealthNote = MutableLiveData<String>()

/*    val gethealthId = MutableLiveData<String>()
    val gethealthTitle = MutableLiveData<String>()
    val gethealthPlace = MutableLiveData<String>()
    val gethealthContent = MutableLiveData<String>()
    val gethealthName = MutableLiveData<String>()
    val gethealthNote = MutableLiveData<String>()


    val gethealth = Health(
        gethealthId.value ?: "",
        gethealthPlace.value ?: "",
        gethealthTitle.value ?: "",
        gethealthName.value ?: "",
        gethealthContent.value ?: "",
        gethealthNote.value ?: "",
    )*/







    /**
     * Call getArticlesResult() on init so we can display status immediately.
     */
    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")

        if (SmartHomeCareApplication.instance.isLiveDataDesign()) {
            getLiveHealthResult()
        } else {
            getHealthResult()

        }
    }

    fun getHealthResult() {

        coroutineScope.launch {

            _status.value = LoadApiStatus.LOADING

            val result = repository.getHealth()

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

    fun getLiveHealthResult() {
        liveHealth = repository.getLiveHealth()
        _status.value = LoadApiStatus.DONE
        _refreshStatus.value = false
    }



}