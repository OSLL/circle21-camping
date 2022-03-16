package com.makentoshe.androidgithubcitemplate.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.databinding.FragmentStartBinding


class StartFragment : Fragment() {
    lateinit var button_menu : Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentStartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)
        button_menu = binding.button2
        button_menu.setOnClickListener{
            view?.findNavController()?.navigate(StartFragmentDirections.actionStartFragmentToMainFragmentDD())
        }
        return binding.root

    }
}
