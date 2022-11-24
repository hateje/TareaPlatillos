package com.example.sem08.model

import android.os.Parcelable
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize


@Parcelize

data class Lugar(

    var id: String,
    val nombre: String,
    val correo: String,
    val telefono: String,
    val web: String

): Parcelable{
    constructor():
            this ("","","","","")
}
