package com.czerny.smarthomecare.data

data class remind(
        var id: Int = 0,
        var data: Long = 0L,
        var time: Long = 0L,
        var name: String = "",
        var content: String = "",
        var note: String

        )