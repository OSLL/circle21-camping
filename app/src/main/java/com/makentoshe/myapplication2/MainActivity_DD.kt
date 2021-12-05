package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView

class MainActivity_DD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dd)

        var add_participant:ImageView = findViewById(R.id.imageView)
        var to_route_plan:Button = findViewById(R.id.button)
        to_route_plan.setOnClickListener {
            Log.d("TAG", "При нажатии на кнопку должен происходить переход к другой активити.")
        }
        add_participant.setOnClickListener {
            Log.d("TAG", "Пока что ничего не происходит (см. TODO)")
        }

    }
}

// #TODO: найти атрибут, отвечающий за перемещение элементов в списке.
// #TODO: разобраться, как работает этот чёртов ChipGroup (как нормально выравнивать элементы?).
// #TODO: сделать RecyclerView для списка участников.
// #TODO: вообще, при нажатии на "Добавить участника" карточка должна переворачиваться (по крайней мере -- реагировать на нажатие).
// #TODO: можно открывать дополнительное мини-окно с вводом информации об участнике.
// #TODO: сделать переход между активити (?)