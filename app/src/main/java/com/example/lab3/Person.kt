package com.example.lab3

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var surname: String,
    var patronymic: String,
    var spec: String
)

//class Person(var name: String, var surname: String, var patronymic: String, var spec: String){}