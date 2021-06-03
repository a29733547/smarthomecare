package com.czerny.smarthomecare.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChatRoom(
        var id: String = "",
        var senderName: String = "",
        var senderImage: String = "",
        var senderEmail: String = "",
        var message: String = "",
        var createdTime: Long = 0L,
): Parcelable

@Parcelize
data class UserInfo(
        var userId : String ="",
        var userName : String = "",
        var userImage : String =""
) : Parcelable

//data class Message(
//        var id : String = "",
//        var senderName : String = "",
//        var senderImage : String = "",
//        var senderEmail : String = "",
//        var text : String = "",
//        var createdTime : Long = 0L
//)