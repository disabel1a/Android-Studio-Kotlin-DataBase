package com.example.lab3

import android.app.Application
import androidx.room.Room
import com.example.lab3.dao.AppDataBase

class App : Application() {
    lateinit var database: AppDataBase

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, AppDataBase::class.java, "person_database").build()
    }
}