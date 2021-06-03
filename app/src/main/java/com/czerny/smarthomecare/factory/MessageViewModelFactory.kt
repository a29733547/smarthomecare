//package com.czerny.smarthomecare.factory
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.czerny.smarthomecare.addremind.item.AddHealthEditViewModel
//import com.czerny.smarthomecare.addremind.item.AddRemindEditViewModel
//import com.czerny.smarthomecare.chatroom.ChatRoomViewModel
//import com.czerny.smarthomecare.data.source.SmartHomeCareRepository
//import com.czerny.smarthomecare.home.HomeViewModel
//import com.czerny.smarthomecare.profile.ProfileViewModel
//import com.czerny.smarthomecare.profile.add.ProfileAddDataViewModel
//import com.czerny.smarthomecare.savedata.modify.SaveDataHealthModifyViewModel
//import com.czerny.smarthomecare.savedata.remind.SaveDataHealthViewModel
//import com.czerny.smarthomecare.savedata.remind.SaveDataRemindViewModel
//import java.lang.IllegalArgumentException
//
//@Suppress("UNCHECKED_CAST")
//class MessageViewModelFactory(
//    private val repository: SmartHomeCareRepository,
//    private val userEmail: String,
//    private val userName: String
//) : ViewModelProvider.Factory{
//
////    override fun <T : ViewModel?> create(modelClass: Class<T>) =
////        with(modelClass) {
////            when {
////
////                isAssignableFrom(ChatRoomViewModel::class.java) ->
////                    ChatRoomViewModel(repository, userEmail, userName)
////
////                else ->
////                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
////            }
////        } as T
//
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ChatRoomViewModel::class.java)) {
//            return ChatRoomViewModel(repository, userEmail, userName) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class : ${modelClass.name}")
//    }
//
//}