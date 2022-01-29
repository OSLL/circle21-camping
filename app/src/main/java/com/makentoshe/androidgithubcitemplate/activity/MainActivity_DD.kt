package com.makentoshe.androidgithubcitemplate.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.adapter.LastPreparationAdapter
import com.makentoshe.androidgithubcitemplate.data.dataPerson

class MainActivity_DD : AppCompatActivity() {
    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<dataPerson>
    private val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    private lateinit var adapter: LastPreparationAdapter
    private lateinit var addindDthToRoadPlan: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        NoActionBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dd)
        userList = ArrayList()
        addsBtn = findViewById(R.id.addindBthAdd)
        recv = findViewById(R.id.recycler_view_id2)
        addsBtn.setOnClickListener { addInfo() }
        addindDthToRoadPlan = findViewById(R.id.addindBrhGoMap)
        adapter = LastPreparationAdapter(userList)

        addindDthToRoadPlan.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun NoActionBar() {
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

    @SuppressLint("NotifyDataSetChanged")
    private fun addInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_item, null)

        val userfirstName = v.findViewById<EditText>(R.id.firstName)
        val userlastName = v.findViewById<EditText>(R.id.lastName)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok") { dialog, _ ->
            val names = userfirstName.text.toString()
            val lastname = userlastName.text.toString()
            recv.layoutManager = layoutManager
            recv.setHasFixedSize(true)
            recv.adapter = adapter
            userList.add(dataPerson(names, lastname))
            adapter.notifyDataSetChanged()
            dialog.dismiss()
            addDialog.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            addDialog.create()
            addDialog.show()
        }
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