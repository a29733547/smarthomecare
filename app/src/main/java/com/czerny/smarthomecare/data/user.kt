package com.czerny.smarthomecare.data

data class user(
        var id: Int = 0,
        var name: String = "",
        var year: Int = 0,
        var month: Int = 0,
        var data: Int = 0,
        var blood: String = "",
        var content: String = "",
        var note: String = "",
        var other: String = "",
        var familyId: Int = 0
)