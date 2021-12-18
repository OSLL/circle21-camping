package com.makentoshe.androidgithubcitemplate

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.androidgithubcitemplate.databinding.PersonItemBinding

class PersonsAdapter : RecyclerView.Adapter<PersonsAdapter.PersonsHolder>() {
    private val personList = ArrayList<dataPersons>()
    class PersonsHolder (item : View) : RecyclerView.ViewHolder(item) {
        private val binding = PersonItemBinding.bind(item)
        fun bind(persons: dataPersons) = with(binding) {
            textView4.text = persons.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return PersonsHolder(view)
    }

    override fun onBindViewHolder(holder: PersonsHolder, position: Int) {
        holder.bind(personList[position])
    }

    override fun getItemCount(): Int {
        return personList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addPerson (persons: dataPersons) {
        personList.add(persons)
        notifyDataSetChanged()
    }
}