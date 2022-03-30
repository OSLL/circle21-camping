package com.makentoshe.androidgithubcitemplate.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.adapter.LastPreparationAdapter
import com.makentoshe.androidgithubcitemplate.data.dataPerson
import com.makentoshe.androidgithubcitemplate.databinding.FragmentMainDDBinding


class MainFragmentDD : Fragment() {
    lateinit var recv: RecyclerView
    lateinit var userList: ArrayList<dataPerson>
    private val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    lateinit var adapter: LastPreparationAdapter
    lateinit var newUser : Button
    lateinit var addindDthToRoadPlan: Button
    private lateinit var yourHike: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentMainDDBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_d_d, container, false)
        userList = ArrayList()
        newUser = binding.addindBthAdd
        recv = binding.recyclerViewId3
        addindDthToRoadPlan = binding.addindBrhGoMap
        adapter = LastPreparationAdapter(userList)
        recv.adapter = adapter
        recv.layoutManager = layoutManager
        yourHike = binding.textYourHike


        val firstNameFrag = arguments?.getString("firstNameBundle")
        val lastNameFrag = arguments?.getString("LastNameBundle")
        val ageFrag = arguments?.getString("Age")
        val getIntArrayList = arguments?.getIntArray("WorkLoad")
        val workLoad = getIntArrayList?.sum().toString()

        if (!firstNameFrag.isNullOrEmpty() && !lastNameFrag.isNullOrEmpty() && !ageFrag.isNullOrEmpty()) {
            yourHike.text = "Вы в походе"
            createRecyclerItem(firstNameFrag,lastNameFrag, ageFrag, workLoad)
        }
        newUser.setOnClickListener {
            view?.findNavController()?.navigate(MainFragmentDDDirections.actionMainFragmentDDToEditFragment())
        }
        addindDthToRoadPlan = binding.addindBrhGoMap
        addindDthToRoadPlan.setOnClickListener {
            view?.findNavController()?.navigate(MainFragmentDDDirections.actionMainFragmentDDToNavigation3())
        }
        return binding.root
}

    @SuppressLint("NotifyDataSetChanged")
    fun createRecyclerItem(firstNameFrag: String, lastNameFrag: String, age: String, workLoad: String) {
        recv.visibility = View.VISIBLE
        recv.setHasFixedSize(true)
        userList.add(
            dataPerson(
                firstNameFrag,
                lastNameFrag,
                "$age лет",
                "$workLoad кг"
            )
        )
        adapter.notifyDataSetChanged()
    }





}