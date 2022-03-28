package com.makentoshe.androidgithubcitemplate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.databinding.FragmentStartBinding


class RulesFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentStartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_rules, container, false)

        return binding.root
    }


}