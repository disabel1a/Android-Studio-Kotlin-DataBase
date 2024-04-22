package com.example.lab3.forms

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.example.lab3.Person
import com.example.lab3.databinding.AddPersonFormBinding

class EditPersonForm (private val person: Person, context: Context, private val listener: OnEditButtonListener) : Dialog(context){
    private lateinit var binding: AddPersonFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddPersonFormBinding.inflate(LayoutInflater.from(context), null, false)
        setContentView(binding.root)

        binding.titleForm.text = "Edit"
        binding.nameField.setText(person.name)
        binding.surnameField.setText(person.surname)
        binding.patronymicField.setText(person.patronymic)
        binding.specField.setText(person.spec)

        binding.submitButton.setOnClickListener {
            person.name = binding.nameField.text.toString()
            person.surname = binding.surnameField.text.toString()
            person.patronymic = binding.patronymicField.text.toString()
            person.spec = binding.specField.text.toString()
            listener.onSubmit(person)
            dismiss()
        }
    }
}