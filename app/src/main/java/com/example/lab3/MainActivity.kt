package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab3.databinding.ActivityMainBinding
import com.example.lab3.forms.AddPersonForm
import com.example.lab3.forms.OnFormSubmitButtonListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonAdapter
    private val personsList: PersonsList
        get() = (application as App).personsList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = LinearLayoutManager(this)
        adapter = PersonAdapter(personsList)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter

        binding.addButton.setOnClickListener {
            val addPersonForm = AddPersonForm(this, object : OnFormSubmitButtonListener {
                override fun onSubmit(person: Person) {
                    personsList.addPerson(person)
                    adapter.notifyDataSetChanged()
                }
            })
            addPersonForm.show()
        }
    }
}