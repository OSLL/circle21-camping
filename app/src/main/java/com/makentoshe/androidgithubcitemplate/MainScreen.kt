package com.makentoshe.androidgithubcitemplate


import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainScreen : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun noActionBarAndBottomNavigation () {
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
    }
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        noActionBarAndBottomNavigation()
        fun toastMaker (text: String?, mediaPlayer : MediaPlayer) {
            Toast.makeText(applicationContext,text, Toast.LENGTH_SHORT).show()
            mediaPlayer.start()
        }
        val mediaPlayer = MediaPlayer.create(this, R.raw.button_click_sound)
        AnimationUtils.loadAnimation(this, R.anim.line_up)
        val textButton: TextView = findViewById(R.id.new_roude_id)
        textButton.setOnClickListener {
        toastMaker("Заметки",mediaPlayer)
        }

        // Тут при нажатии на текст "Новая заметка" , будет всплывать уведомление
        val textButton2: TextView = findViewById(R.id.plan_id)
        textButton2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            mediaPlayer.start()
        }

        val textButton3: TextView = findViewById(R.id.review_id)
        textButton3.setOnClickListener {
            toastMaker("Советы и отзывы", mediaPlayer)
        }

        val textButton4: TextView = findViewById(R.id.hike_id)
        textButton4.setOnClickListener {
            val intent = Intent(this, MainActivity_DD::class.java)
            startActivity(intent)
            mediaPlayer.start()
        }

        val textButton5: TextView = findViewById(R.id.eat_id)
        textButton5.setOnClickListener {
            toastMaker("Еда", mediaPlayer)
        }

        val buttonStart: Button = findViewById(R.id.button)
        buttonStart.setOnClickListener {
            toastMaker("Начать", mediaPlayer)
        }

        val backImageButton: ImageButton = findViewById(R.id.back_view_icon_id)
        backImageButton.setOnClickListener {
            toastMaker("Назад", mediaPlayer)
        }

        val settingImageButton: ImageButton = findViewById(R.id.setting_icon_id)
        settingImageButton.setOnClickListener {
            toastMaker("Настройки", mediaPlayer)
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
}

