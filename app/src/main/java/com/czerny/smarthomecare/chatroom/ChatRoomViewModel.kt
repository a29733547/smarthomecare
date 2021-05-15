package com.czerny.smarthomecare.chatroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.data.Message

class ChatRoomViewModel: ViewModel() {

    private val _Mockdata = MutableLiveData<List<Message>>() //w2p2
    val Mockdata: LiveData<List<Message>>
        get() = _Mockdata

    var editableList: MutableList<Message> = mutableListOf()

    val enterMessage = MutableLiveData<String>()
}