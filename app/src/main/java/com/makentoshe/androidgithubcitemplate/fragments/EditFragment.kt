package com.makentoshe.androidgithubcitemplate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.databinding.FragmentEditBinding


class EditFragment : Fragment() {

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var textWorkload : TextView
    private lateinit var btnAddUser: Button
    private lateinit var btnBack: Button
    private lateinit var age: EditText
    private lateinit var textToWorkLoad: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentEditBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)
        firstName = binding.editName
        lastName = binding.editLastName
        btnBack = binding.btnBack
        age = binding.editAge
        textWorkload = binding.textWorkload
        btnAddUser = binding.btnAdd
        textToWorkLoad = binding.textWorkload
        val getArrayInt = arguments?.getIntArray("WorkLoadBundle")


        val bundle = Bundle()
        btnAddUser.setOnClickListener {
            if (!firstName.text.isNullOrEmpty() && !lastName.text.isNullOrEmpty() && !age.text.isNullOrEmpty()) {
                val name1 = firstName.text.toString()
                val name2 = lastName.text.toString()
                val age = age.text.toString()
                bundle.putString("firstNameBundle",name1)
                bundle.putString("LastNameBundle", name2)
                bundle.putString("Age",age)
                bundle.putIntArray("WorkLoad", getArrayInt)
                findNavController().navigate(R.id.action_editFragment_to_mainFragmentDD, bundle)
            }
        }
        textToWorkLoad.setOnClickListener {
            findNavController().navigate(R.id.action_editFragment_to_workLoadFragment)
        }

//        btnBack.setOnClickListener {
//            findNavController().navigate(EditFragmentDirections.actionEditFragmentToMainFragmentDD())
//        }
        return binding.root
    }




}