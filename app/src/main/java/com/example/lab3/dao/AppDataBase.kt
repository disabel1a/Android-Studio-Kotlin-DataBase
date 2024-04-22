package com.example.lab3.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab3.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}