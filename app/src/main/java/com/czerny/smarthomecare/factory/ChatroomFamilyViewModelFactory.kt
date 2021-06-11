package com.czerny.smarthomecare.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.czerny.smarthomecare.chatroom.ChatRoomViewModel
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository

@Suppress("UNCHECKED_CAST")
class ChatroomFamilyViewModelFactory constructor(
    private val repository: SmartHomeCareRepository,
    private val family: String
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(ChatRoomViewModel::class.java) ->
                        ChatRoomViewModel(repository, family )


                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T
}