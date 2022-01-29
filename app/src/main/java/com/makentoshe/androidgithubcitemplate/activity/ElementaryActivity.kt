package com.makentoshe.androidgithubcitemplate.activity


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.makentoshe.androidgithubcitemplate.R

class ElementaryActivity : AppCompatActivity(), View.OnClickListener {

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun noActionBarAndBottomNavigation () {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        noActionBarAndBottomNavigation()
    val button_menu : Button = findViewById(R.id.button2)
    button_menu.setOnClickListener{
        val intent = Intent(this, MainActivity_DD::class.java)
        startActivity(intent)
    }


    }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onResume() {
        super.onResume()
        noActionBarAndBottomNavigation()
    }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onPause() {
        super.onPause()
        noActionBarAndBottomNavigation()
    }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onStop() {
        super.onStop()
        noActionBarAndBottomNavigation()
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }


}
