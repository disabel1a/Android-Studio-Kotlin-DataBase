package com.example.lab3

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.dao.PersonDao
import com.example.lab3.forms.EditPersonForm
import com.example.lab3.forms.OnEditButtonListener
import com.example.lab3.forms.OnFormSubmitButtonListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonAdapter(private val personDao: PersonDao) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private var persons: List<Person> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_of_recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(persons[position])
    }

    override fun getItemCount(): Int = persons.size

    fun setPersonsList(persons: List<Person>) {
        this.persons = persons
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val mainTextView: TextView = itemView.findViewById(R.id.mainText)
        private val extraTextView: TextView = itemView.findViewById(R.id.extraText)

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener {
                showPopupMenu(it, adapterPosition)
                true
            }
        }

        fun bind(person: Person) {
            val mainField = "${person.name} ${person.surname} ${person.patronymic}"
            val extraField = "Job: ${person.spec}"

            mainTextView.text = mainField
            extraTextView.text = extraField
        }

        override fun onClick(v: View?) {
            // Handle item clicks here
        }
    }

    private fun showPopupMenu(view: View, position: Int) {
        val popupMenu = PopupMenu(view.context, view)

        popupMenu.menu.add(0, 1, Menu.NONE, "Edit")
        popupMenu.menu.add(0, 2, Menu.NONE, "Remove")

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                1 -> {
                    val person = persons[position]
                    val editPersonForm = EditPersonForm(person, view.context, object : OnEditButtonListener {
                        override fun onSubmit(person: Person) {
                            CoroutineScope(Dispatchers.IO).launch {
                                personDao.update(
                                    person.id,
                                    person.name,
                                    person.surname,
                                    person.patronymic,
                                    person.spec
                                )
                            }
                        }
                    })
                    editPersonForm.show()
                    true
                }
                2 -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        val person = persons[position]
                        personDao.delete(person.id)
                    }
                    notifyItemRemoved(position)
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
}


//class PersonAdapter(private val personsList: PersonsList, private val personDao: PersonDao) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
//
//    private lateinit var listener: OnItemClickListener
//    private var persons = personsList.getPersons();
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_of_recycler_view, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(persons[position])
//        holder.itemView.setOnClickListener {
//            showPopupMenu(it, position)
//        }
//    }
//
//    override fun getItemCount(): Int = persons.size
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(person: Person) {
//            val mainField = "${person.name} ${person.surname} ${person.patronymic}"
//            val extraField = "Job: ${person.spec}"
//
//            itemView.findViewById<TextView>(R.id.mainText).text = mainField
//            itemView.findViewById<TextView>(R.id.extraText).text = extraField
//        }
//    }
//
//    private fun showPopupMenu(view: View, position: Int) {
//        val popupMenu = PopupMenu(view.context, view)
//
//        popupMenu.menu.add(0, 1, Menu.NONE, "Edit")
//        popupMenu.menu.add(0, 2, Menu.NONE, "Remove")
//
//        popupMenu.setOnMenuItemClickListener {
//            when(it.itemId) {
//                1 -> {
//                    val editPersonForm = EditPersonForm(persons[position], view.context, object: OnEditButtonListener {
//                        override fun onSubmit(person: Person) {
//                            personDao.update(person.id, person.name, person.surname, person.patronymic, person.spec)
//                            notifyItemChanged(position)
//                        }
//                    })
//                    editPersonForm.show()
//                }
//                2 -> {
//                    personDao.delete(personsList.getPersonId(position))
//                    personsList.removerPerson(position)
//                    notifyItemRemoved(position)
//                }
//            }
//            true
//        }
//
//        popupMenu.show()
//    }
//
//    interface OnItemClickListener {
//        fun OnItemClickListener(position: Int)
//    }
//}