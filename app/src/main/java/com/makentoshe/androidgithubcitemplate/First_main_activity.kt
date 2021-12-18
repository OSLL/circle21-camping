package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.makentoshe.androidgithubcitemplate.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_first_main_activite.*

class First_main_activity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val personIdName = listOf("Сергей Петрович", "Ренат Бахримов", "Ибрагим Даланович", "Никита Сосно", "Егор Махиров")
    private var adapter = PersonsAdapter()
    private var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    override   fun   onWindowFocusChanged  ( hasFocus :   Boolean ) {
        super .onWindowFocusChanged(hasFocus)
        WindowCompat .setDecorFitsSystemWindows(window,  false )
        WindowInsetsControllerCompat (window, findViewById<ConstraintLayout>( R .id.constraint)). let  {
            it.hide( WindowInsetsCompat . Type .systemBars())
            it.systemBarsBehavior  =   WindowInsetsControllerCompat . BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
    private fun init () {
        binding.apply {
            personList.layoutManager = GridLayoutManager (this@First_main_activity,2)
            personList.adapter = adapter
            button3.setOnClickListener {
                if (index > 2) index = 0
                val person = dataPersons(personIdName[index])
                adapter.addPerson(person)
                index++
            }
        }
    }
}