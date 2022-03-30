package com.makentoshe.androidgithubcitemplate.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.data.dataPerson

class LastPreparationAdapter(private val userList: MutableList<dataPerson>) : RecyclerView.Adapter<LastPreparationAdapter.UserViewHolder>() {

    inner class UserViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val name: TextView = v.findViewById(R.id.second_name_id)
        val first_name: TextView = v.findViewById(R.id.first_name_id)
        val age: TextView = v.findViewById(R.id.age_id)
        val workLoad: TextView = v.findViewById(R.id.work_load)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.person_item,parent,false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Log.d("MyLog","NotBag: ${userList.size}")
        holder.name.text = userList[position].name
        holder.first_name.text = userList[position].first_name
        holder.age.text = userList[position].age
        holder.workLoad.text = userList[position].workload
    }
    override fun getItemCount(): Int {
        return userList.size
    }
}

