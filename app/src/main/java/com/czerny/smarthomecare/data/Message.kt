package com.czerny.smarthomecare.data

data class Message(
        var id : Int = 0,
        var senderName : String = "",
//        var senderImage : String = "",
//        var senderEmail : String = "",
        var text : String = "",
        var createdTime : Long = 0L
)