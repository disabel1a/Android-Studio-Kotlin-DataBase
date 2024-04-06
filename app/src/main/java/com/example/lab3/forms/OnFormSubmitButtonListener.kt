package com.example.lab3.forms

import com.example.lab3.Person

interface OnFormSubmitButtonListener {
    fun onSubmit(person: Person)
}