package com.makentoshe.androidgithubcitemplate.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.makentoshe.androidgithubcitemplate.R


class EditFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: EditFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)
        val firstName : EditText = binding.editName
        val lastName : EditText = binding.editLastName
        val age : EditText = binding.editAge
        val btnBack : Button = binding.btnBack
        val textWorkload : TextView = binding.textWorkload
        val btnAddUser : Button = binding.btnAdd


        val bundle = Bundle()

        btnAddUser.setOnClickListener {
            if (!firstName.text.isNullOrEmpty() && !lastName.text.isNullOrEmpty() && !age.text.isNullOrEmpty()) {
                val name1 = firstName.text
                val name2 = lastName.text
                val age1 = age.text
                bundle.putString("FirstName", name1.toString())
                bundle.putString("LastName", name2.toString())
                bundle.putString("Age", age1.toString())
                findNavController().navigate(com.example.android.navigation.EditFragmentDirections.actionEditFragmentToMainFragmentDD())
            }
        }
        btnBack.setOnClickListener {

        }
        return binding.root
    }



}