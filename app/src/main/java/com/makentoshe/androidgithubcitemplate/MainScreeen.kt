package com.makentoshe.androidgithubcitemplate


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.scaleMatrix
import kotlinx.android.synthetic.main.activity_main_screen.*


class MainScreeen : AppCompatActivity() {
    private lateinit var button : Button
    private lateinit var imageButton: ImageButton
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }

        val textbutton: TextView = findViewById(R.id.new_roude_id)
            val text_toast = "Переход на экран заметки, скоро..."
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text_toast, duration)
            toast.show()

        // Тут при нажатии на текст "Новая заметка" , будет всплывать уведомление
        val textbutton2: TextView = findViewById(R.id.plan_id)
        textbutton2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val textbutton3: TextView = findViewById(R.id.review_id)
        textbutton3.setOnClickListener {
            val text_toast3 = "Переход на экран советы и отзывы, скоро..."
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text_toast3, duration)
            toast.show()
        }

        val textbutton4: TextView = findViewById(R.id.hike_id)
        textbutton4.setOnClickListener {
            val text_toast4 = "Переход на экран подготовка к походу, скоро..."
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text_toast4, duration)
            toast.show()
        }

        val textbutton5: TextView = findViewById(R.id.eat_id)
        textbutton5.setOnClickListener {
            val text_toast5 = "Переход на экран питание, скоро..."
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text_toast5, duration)
            toast.show()
        }

        val ButtonStart: Button = findViewById(R.id.button)
        ButtonStart.setOnClickListener {
            val Start = "Не переживайте, скоро начнем :)"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, Start, duration)
            toast.show()
        }

        val BackImageButton: ImageButton = findViewById(R.id.back_view_icon_id)
        BackImageButton.setOnClickListener {
            val Back = "Подождите, назад вернуться всегда успеете"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, Back, duration)
            toast.show()
        }

        val SettingImageButton: ImageButton = findViewById(R.id.setting_icon_id)
        SettingImageButton.setOnClickListener {
            val Setting = "Для чего тебе настройки, если приложение еще не готово?"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, Setting, duration)
            toast.show()
        }
    }
    }
    //fun ButtonClick(view: View) {
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
    //}

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