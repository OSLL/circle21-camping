package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main_dd.*
import java.util.ArrayList

class MainActivity_DD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dd)

//        val add_participant:ImageView = findViewById(R.id.imageView)
        val to_route_plan:Button = findViewById(R.id.button_go_map)
        to_route_plan.setOnClickListener {
            Log.d("TAG", "При нажатии на кнопку должен происходить переход к другой активити.")
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
//        add_participant.setOnClickListener {
//            Log.d("TAG", "Пока что ничего не происходит (см. TODO)")
//        }
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
        val data2 : MutableList<Person_d> = ArrayList()
        for (i in 1..10)
            data2.add(Person_d("Добавить участника $i"))
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        val adapter = PersonsPreparetion_Adapter(data2)
        recycler_view_id2.layoutManager = layoutManager
        recycler_view_id2.setHasFixedSize(true)
        recycler_view_id2.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
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

    override fun onPause() {
        super.onPause()
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

    override fun onStop() {
        super.onStop()
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
}
// #TODO: убрать ActionBar вверху экрана, открывается при onStop, onResume, onPause by Nikita
// #TODO: найти атрибут, отвечающий за перемещение элементов в списке.
// #TODO: разобраться, как работает этот чёртов ChipGroup (как нормально выравнивать элементы?).
// #TODO: сделать RecyclerView для списка участников.
// #TODO: вообще, при нажатии на "Добавить участника" карточка должна переворачиваться (по крайней мере -- реагировать на нажатие).
// #TODO: можно открывать дополнительное мини-окно с вводом информации об участнике.
// #TODO: сделать переход между активити (?)