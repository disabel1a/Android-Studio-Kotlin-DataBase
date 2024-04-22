package com.example.lab3.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.lab3.Person

@Dao
interface PersonDao {
    @Insert
    fun insert(person: Person) : Long

    @Query("SELECT * FROM person ORDER BY id DESC LIMIT 1")
    fun getLast(): Person

    @Query("SELECT * FROM person")
    fun getAll(): LiveData<List<Person>>

    @Query("DELETE FROM person")
    fun deleteAll()

    @Query("DELETE FROM person WHERE id = :id")
    fun delete(id: Int)

    //@Query("INSERT INTO person(name, surname, patronymic, spec) VALUES(:name, :surname, :patronymic, :spec)")
    //fun insert(name: String, surname: String, patronymic: String, spec: String)

    @Query("UPDATE person SET name = :name, surname = :surname, patronymic = :patronymic, spec = :spec WHERE id = :id")
    fun update(id: Int, name: String, surname: String, patronymic: String, spec: String);
}