package com.makentoshe.androidgithubcitemplate


import android.os.Build
import android.os.Bundle
//import android.util.Log
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainScreeen : AppCompatActivity() {
    private lateinit var button : Button
    private lateinit var imageButton: ImageButton
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        button = findViewById(R.id.button)
//            imageButton = findViewById(R.id.imageButton3)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        val textbutton: TextView = findViewById(R.id.new_roude_id)
        textbutton.setOnClickListener {
            val text_toast = "Переход на экран заметки, скоро..."
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text_toast, duration)
            toast.show()
        }
        // Тут при нажатии на текст "Новая заметка" , будет всплывать уведомление
        val textbutton2: TextView = findViewById(R.id.plan_id)
        textbutton2.setOnClickListener {
            val text_toast2 = "Переход на экран маршрута, скоро..."
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text_toast2, duration)
            toast.show()
        }



    }

//
//    fun New_the_note (view: View) {
//        Log.d("My Tag", "NewTheNote complete")
//    }
//    fun route (view: View) {
//        Log.d("My Tag", "route complete")
//    }
//    fun preparation (view: View) {
//        Log.d("My Tag", "preparation complete")
//    }
//    fun ButtonStop (view: View) {
//        Log.d("My Tag", "ButtonStop complete")
//    }
//    fun EatButton (view: View) {
//        Log.d("My Tag", "EatButton complete")
//    }
//    fun advice (view: View) {
//        Log.d("My Tag", "advice complete")
//    }
//    fun new_route (view: View) {
//        Log.d("My Tag", "new_route complete")
//    }
//    fun setting (view: View) {
//        Log.d("My Tag", "setting complete")
//    }

}