package com.subhamassignment.mvvmnoteapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NT")
data class Table(
    @PrimaryKey(autoGenerate = true)
    val id:Long?,
    val note:String,
    val time:String,
    val date:String
)
