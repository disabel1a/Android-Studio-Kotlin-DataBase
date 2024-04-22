package com.example.lab3.forms

import com.example.lab3.Person

interface OnFormSubmitButtonListener {

    fun onSubmit(name: String, surname: String, patronymic: String, spec: String)
}