package com.makentoshe.androidgithubcitemplate.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.makentoshe.androidgithubcitemplate.R

class GoHikeActivity : AppCompatActivity() {
    private fun noActionBarAndBottomNavigation() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        noActionBarAndBottomNavigation()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_main_activite) }
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