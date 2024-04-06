package com.example.lab3

import com.github.javafaker.Faker

class PersonsList {
    private var persons = mutableListOf<Person>()

    init {
        val faker = Faker.instance() // Переменная для создания случайных данных

        persons = (1..25).map {
            Person(
                name = faker.name().firstName(),
                surname = faker.name().lastName(),
                patronymic = faker.name().nameWithMiddle(),
                spec = faker.job().title()
            )
        }.toMutableList()
    }

    fun addPerson(person: Person) {
        persons.add(person)
    }

    fun getPersons() : List<Person> = persons

    fun removerPerson(position : Int) {
        persons.removeAt(position)
    }
}