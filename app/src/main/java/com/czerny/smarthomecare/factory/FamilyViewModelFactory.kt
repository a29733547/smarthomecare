package com.czerny.smarthomecare.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.czerny.smarthomecare.addremind.AddRemindViewModel
import com.czerny.smarthomecare.addremind.item.AddRemindEditViewModel
import com.czerny.smarthomecare.chatroom.ChatRoomViewModel
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.home.HomeViewModel
import com.czerny.smarthomecare.savedata.remind.SaveDataRemindViewModel

@Suppress("UNCHECKED_CAST")
class FamilyViewModelFactory constructor(
    private val repository: SmartHomeCareRepository,
    private val family: String
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(ChatRoomViewModel::class.java) ->
                    ChatRoomViewModel(repository, family)

                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(repository, family)

                isAssignableFrom(AddRemindViewModel::class.java) ->
                    AddRemindViewModel(repository, family)

                isAssignableFrom(AddRemindEditViewModel::class.java) ->
                    AddRemindEditViewModel(repository, family)


                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}