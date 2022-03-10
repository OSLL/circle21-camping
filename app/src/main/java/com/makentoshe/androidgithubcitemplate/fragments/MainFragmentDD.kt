package com.makentoshe.androidgithubcitemplate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.adapter.LastPreparationAdapter
import com.makentoshe.androidgithubcitemplate.data.dataPerson
import com.makentoshe.androidgithubcitemplate.databinding.FragmentMainDDBinding


class MainFragmentDD : Fragment() {
    lateinit var recv: RecyclerView
    lateinit var userList: ArrayList<dataPerson>
    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    lateinit var adapter: LastPreparationAdapter
    lateinit var newUser : FloatingActionButton
    lateinit var addindDthToRoadPlan: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: MainFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_d_d, container, false)
        userList = ArrayList()
        newUser = binding.addindBthAdd
        recv = binding.recyclerViewId3
        recv.visibility = View.GONE

        newUser.setOnClickListener {
            recv.visibility = View.VISIBLE
            newUser.visibility = View.GONE
            val fragment = Fragment()
            val firstNameFrag = fragment.arguments?.getString("FirstName")
            val lastNameFrag = fragment.arguments?.getString("LastName")
            val ageFrag = fragment.arguments?.getString("Age")
            ////////////////////////////////// тут был обработчик событий
            recv.layoutManager = layoutManager
            recv.setHasFixedSize(true)
            recv.adapter = adapter
            userList.add(
                dataPerson(
                    "Имя: ${firstNameFrag.toString()}",
                    "Фамилия: ${lastNameFrag.toString()} ",
                )
            )
            adapter.notifyDataSetChanged()
            /////////////////////////////
        }
        addindDthToRoadPlan = binding.addindBrhGoMap
        adapter = LastPreparationAdapter(userList)
        addindDthToRoadPlan.setOnClickListener {
            view?.findNavController()?.navigate(com.example.android.navigation.MainFragmentDDDirections.actionMainFragmentDDToNavigation3())
        }
        return binding.root
}

}