package com.czerny.smarthomecare.chatroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.data.Message
import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
import com.czerny.smarthomecare.login.UserManager

class ChatRoomViewModel(private val repository: SmartHomeCareRepository): ViewModel() {

    private val _Mockdata = MutableLiveData<List<Message>>()
    val Mockdata: LiveData<List<Message>>
        get() = _Mockdata

    var editableList: MutableList<Message> = mutableListOf()

    val enterMessage = MutableLiveData<String>()

    fun getMessage(): Message {
        return Message(
            id = "",
            senderName = UserManager.user.name,
            senderImage = UserManager.user.image,
            senderEmail = UserManager.user.email,
            text = enterMessage.value.toString(),
            createdTime = 0L
        )
    }
}