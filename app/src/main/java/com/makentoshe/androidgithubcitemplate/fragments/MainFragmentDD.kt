package com.makentoshe.androidgithubcitemplate.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.adapter.LastPreparationAdapter
import com.makentoshe.androidgithubcitemplate.data.dataPerson
import com.makentoshe.androidgithubcitemplate.databinding.FragmentMainDDBinding


class MainFragmentDD : Fragment() {
    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<dataPerson>
    private val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    private lateinit var adapter: LastPreparationAdapter
    private lateinit var newUser : ImageButton
    private lateinit var addindDthToRoadPlan: Button
    private val args: MainFragmentDDArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentMainDDBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_d_d, container, false)
        val amountFirstName = args.nameArgs
        val amountLastName = args.lastNameArgs
        if (amountFirstName.isNotEmpty() && amountLastName.isNotEmpty()) {
            createRecyclerItem(amountFirstName,amountLastName)
        }
        userList = ArrayList()
        newUser = binding.addindBthAdd
        recv = binding.recyclerViewId3
        recv.visibility = View.GONE
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
    fun createRecyclerItem(amountFirst: String, amountLast: String) {
        recv.visibility = View.VISIBLE
        newUser.visibility = View.GONE
        recv.layoutManager = layoutManager
        recv.setHasFixedSize(true)
        recv.adapter = adapter
        adapter = LastPreparationAdapter(userList)
        userList.add(
            dataPerson(
                "Имя: $amountFirst",
                "Фамилия: $amountLast ",
            )
        )
        adapter.notifyDataSetChanged()
    }

}