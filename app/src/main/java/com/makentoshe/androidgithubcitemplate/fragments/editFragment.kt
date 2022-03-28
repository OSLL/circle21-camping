package com.makentoshe.androidgithubcitemplate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.databinding.FragmentEditBinding


class EditFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentEditBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)
        val firstName: EditText = binding.editName
        val lastName : EditText = binding.editLastName
        val age : EditText = binding.editAge
        val btnBack : Button = binding.btnBack
        //val textWorkload : TextView = binding.textWorkload
        val btnAddUser : Button = binding.btnAdd




        btnAddUser.setOnClickListener {
            if (!firstName.text.isNullOrEmpty() && !lastName.text.isNullOrEmpty() && !age.text.isNullOrEmpty()) {
                val name1 = firstName.text.toString()
                val name2 = lastName.text.toString()
                //val age1 = age.text.toString().toInt()

                val action = EditFragmentDirections.actionEditFragmentToMainFragmentDD(name1,name2)
                view?.findNavController()?.navigate(action)
            }
        }


        btnBack.setOnClickListener {
        }
        return binding.root
    }



}