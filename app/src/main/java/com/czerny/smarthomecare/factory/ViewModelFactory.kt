package app.appworks.school.publisher.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.czerny.smarthomecare.MainViewModel
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.home.HomeViewModel
import com.czerny.smarthomecare.savedata.remind.SaveDataHealthViewModel

/**
 * Created by Wayne Chen on 2020-01-15.
 *
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val repository: SmartHomeCareRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {

                isAssignableFrom(SaveDataHealthViewModel::class.java) ->
                    SaveDataHealthViewModel(repository)

//                isAssignableFrom(MainViewModel::class.java) ->
//                    MainViewModel(repository)
//
//                isAssignableFrom(HomeViewModel::class.java) ->
//                    HomeViewModel(repository)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
