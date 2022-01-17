package com.makentoshe.androidgithubcitemplate.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.makentoshe.androidgithubcitemplate.R

class First_main_activity : AppCompatActivity() {
    private fun noActionBarAndBottomNavigation () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        noActionBarAndBottomNavigation()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_main_activite)
//        val data2: MutableList<Person_d> = ArrayList()
//        for (i in 1..10)
    }
//        data2.add(Person_d("Имя Фамилия $i"))
//        val bth_roude : Button = findViewById(R.id.route_bth)
//        bth_roude.setOnClickListener{
//            val intent = Intent (this, MainActivity::class.java)
//            startActivity(intent)
//        }

//    val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
//    val adapter = PersonsAdapter(data2)
//    recycler_view_id.layoutManager = layoutManager
//    recycler_view_id.setHasFixedSize(true)
//    recycler_view_id.adapter = adapter
//    }

            override fun onPause() {
            noActionBarAndBottomNavigation()
            super.onPause()
        }

        override fun onStart() {
            noActionBarAndBottomNavigation()
            super.onStart()
        }

        override fun onResume() {
            noActionBarAndBottomNavigation()
            super.onResume()
        }

        override fun onStop() {
            noActionBarAndBottomNavigation()
            super.onStop()
        }
    }

