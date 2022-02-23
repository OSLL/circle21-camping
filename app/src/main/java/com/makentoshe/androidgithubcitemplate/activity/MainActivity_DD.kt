package com.makentoshe.androidgithubcitemplate.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.adapter.LastPreparationAdapter
import com.makentoshe.androidgithubcitemplate.data.dataPerson


class MainActivity_DD : AppCompatActivity() {
    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<dataPerson>
    private val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    private lateinit var adapter: LastPreparationAdapter
    private lateinit var newUser : FloatingActionButton
    private lateinit var addindDthToRoadPlan: Button

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun NoButton() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("InflateParams", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        NoActionBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dd)

        userList = ArrayList()
        newUser = findViewById(R.id.addindBthAdd)
        recv = findViewById(R.id.recycler_view_id2)
        recv.visibility = View.GONE

        newUser.setOnClickListener {
            recv.visibility = View.VISIBLE
            newUser.visibility = View.GONE
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.bottom_sheet_layout,null)
            val close = view.findViewById<ImageButton>(R.id.close)
            val btnAddPerson = view.findViewById<Button>(R.id.button_add_person)
            close.setOnClickListener {
                dialog.dismiss()
                NoActionBar()
            }

            btnAddPerson.setOnClickListener {
                NoButton()
                val inflter = LayoutInflater.from(this)
                val v = inflter.inflate(R.layout.bottom_sheet_layout, null)
                val userfirstName = v.findViewById<EditText>(R.id.EditTextfirstname)
                val userlastName = v.findViewById<EditText>(R.id.editTextTextLastName)
                recv.layoutManager = layoutManager
                recv.setHasFixedSize(true)
                recv.adapter = adapter
                userList.add(
                    dataPerson(
                        "Имя: ${userfirstName.text}",
                        "Фамилия: ${userlastName.text} ",
                    )
                )
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            dialog.setContentView(view)
            dialog.show()
        }

        addindDthToRoadPlan = findViewById(R.id.addindBrhGoMap)
        adapter = LastPreparationAdapter(userList)
        addindDthToRoadPlan.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun NoActionBar() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }





        override fun onRestart() {
            super.onRestart()
            NoActionBar()
        }

        override fun onStart() {
            super.onStart()
            NoActionBar()
        }

        override fun onResume() {
            super.onResume()
            NoActionBar()
        }

        override fun onPause() {
            super.onPause()
            NoActionBar()
        }

        override fun onStop() {
            super.onStop()
            NoActionBar()
        }

        override fun onDestroy() {
            super.onDestroy()
            NoActionBar()
        }


}

// #TODO: убрать ActionBar вверху экрана, открывается при onStop, onResume, onPause by Nikita
// #TODO: найти атрибут, отвечающий за перемещение элементов в списке.
// #TODO: разобраться, как работает этот чёртов ChipGroup (как нормально выравнивать элементы?).
// #TODO: сделать RecyclerView для списка участников.
// #TODO: вообще, при нажатии на "Добавить участника" карточка должна переворачиваться (по крайней мере -- реагировать на нажатие).
// #TODO: можно открывать дополнительное мини-окно с вводом информации об участнике.
// #TODO: сделать переход между активити (?)