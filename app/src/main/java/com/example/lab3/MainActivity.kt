package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab3.databinding.ActivityMainBinding
import com.example.lab3.forms.AddPersonForm
import com.example.lab3.forms.OnFormSubmitButtonListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val personDao = (application as App).database.personDao()

        val layoutManager = LinearLayoutManager(this)
        adapter = PersonAdapter(personDao)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        personDao.getAll().observe(this) { persons ->
            adapter.setPersonsList(persons)
        }

        binding.addButton.setOnClickListener {
            val addPersonForm = AddPersonForm(this, object : OnFormSubmitButtonListener {
                override fun onSubmit(name: String, surname: String, patronymic: String, spec: String) {
                    val person = Person(name = name, surname = surname, patronymic = patronymic, spec = spec)
                    CoroutineScope(Dispatchers.IO).launch {
                        personDao.insert(person)
                    }
                }
            })
            addPersonForm.show()
        }
    }
}


//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var adapter: PersonAdapter
//    private val personDao by lazy { (application as App).database.personDao() }
//    private val personsList: PersonsList
//        get() {
//            val persons = personDao.getAll().value ?: return PersonsList()
//            return PersonsList(persons)
//        }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val manager = LinearLayoutManager(this)
//        adapter = PersonAdapter(personsList, personDao)
//        binding.recyclerView.layoutManager = manager
//        binding.recyclerView.adapter = adapter
//
//        binding.addButton.setOnClickListener {
//            val addPersonForm = AddPersonForm(this, object : OnFormSubmitButtonListener {
//                override fun onSubmit(name: String, surname: String, patronymic: String, spec: String) {
//                    val person = Person(name = name, surname = surname, patronymic = patronymic, spec = spec)
//                    CoroutineScope(Dispatchers.IO).launch {
//                        personDao.insert(person)
//                    }
//                }
//            })
//            addPersonForm.show()
//        }
//    }
//}