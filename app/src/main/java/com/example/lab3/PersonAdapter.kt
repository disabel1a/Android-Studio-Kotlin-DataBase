package com.example.lab3

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.forms.EditPersonForm
import com.example.lab3.forms.OnFormSubmitButtonListener

class PersonAdapter(private val personsList: PersonsList) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private lateinit var listener: OnItemClickListener
    private var persons = personsList.getPersons();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_of_recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(persons[position])
        holder.itemView.setOnClickListener {
            showPopupMenu(it, position)
        }
    }

    override fun getItemCount(): Int = persons.size

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(person: Person) {
            val mainField = "${person.name} ${person.surname} ${person.patronymic}"
            val extraField = "Job: ${person.spec}"

            itemView.findViewById<TextView>(R.id.mainText).text = mainField
            itemView.findViewById<TextView>(R.id.extraText).text = extraField
        }
    }

    private fun showPopupMenu(view: View, position: Int) {
        val popupMenu = PopupMenu(view.context, view)

        popupMenu.menu.add(0, 1, Menu.NONE, "Edit")
        popupMenu.menu.add(0, 2, Menu.NONE, "Remove")

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                1 -> {
                    val editPersonForm = EditPersonForm(persons[position], view.context, object : OnFormSubmitButtonListener {
                        override fun onSubmit(person: Person) {
                            notifyItemChanged(position)
                        }
                    })
                    editPersonForm.show()
                }
                2 -> {
                    personsList.removerPerson(position)
                    notifyItemRemoved(position)
                }
            }
            true
        }

        popupMenu.show()
    }

    interface OnItemClickListener {
        fun OnItemClickListener(position: Int)
    }
}