package com.example.lab3.forms

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.example.lab3.Person
import com.example.lab3.databinding.AddPersonFormBinding

class AddPersonForm(context: Context, private val listener: OnFormSubmitButtonListener) : Dialog(context) {
    private lateinit var binding: AddPersonFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddPersonFormBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            val name = binding.nameField.text.toString()
            val surname = binding.surnameField.text.toString()
            val patronymic = binding.patronymicField.text.toString()
            val spec = binding.specField.text.toString()
            listener.onSubmit(Person(name, surname, patronymic, spec))
            dismiss()
        }
    }
}