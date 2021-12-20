package com.makentoshe.androidgithubcitemplate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_preparation_person.view.*

class PersonsPreparetion_Adapter (private val data: List<Person_d>) : RecyclerView.Adapter<PersonsPreparetion_Adapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = LayoutInflater.from(parent.context).inflate(R.layout.activity_preparation_person, parent, false)
        return ViewHolder(b)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = data[position].name

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder
    internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle : TextView
            get() = itemView.textView4
        val card: CardView
            get() = itemView.card_preparation
    }
}