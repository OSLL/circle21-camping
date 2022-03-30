package com.makentoshe.androidgithubcitemplate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.databinding.FragmentWorkLoadBinding

class WorkLoadFragment : Fragment() {


   private lateinit var workLoadEdit: EditText
   private lateinit var weightEdit: EditText
   private lateinit var buttonAddEdit: Button
   private lateinit var buttonReady: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentWorkLoadBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_work_load, container, false)
        workLoadEdit = binding.itemWorkload
        weightEdit = binding.weightWorkload
        buttonAddEdit = binding.buttonNewWorkload
        buttonReady = binding.buttonReady
        val bundle = Bundle()
        val ListWorkLoad = mutableListOf<Int>()
        buttonAddEdit.setOnClickListener {
            ListWorkLoad.add(weightEdit.text.toString().toInt())
        }

        buttonReady.setOnClickListener {
            val intList = ListWorkLoad.size
            val ArrayWorkLoad = IntArray(intList)
            for (i in ArrayWorkLoad.indices) {
                for (j in ListWorkLoad) {
                ArrayWorkLoad.set(i,j)
                }
            }
            bundle.putIntArray("WorkLoadBundle", ArrayWorkLoad)
            findNavController().navigate(R.id.action_workLoadFragment_to_editFragment,bundle)
        }

        return binding.root
    }


}