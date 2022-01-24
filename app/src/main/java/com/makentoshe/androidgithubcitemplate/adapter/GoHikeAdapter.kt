package com.makentoshe.androidgithubcitemplate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.data.dataPerson
import kotlinx.android.synthetic.main.person_item.view.*

class GoHikeAdapter(private val data: List<dataPerson>) : RecyclerView.Adapter<GoHikeAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return ViewHolder(v)

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
            get() = itemView.first_name_id
        val card: CardView
        get() = itemView.card
    }
}