package com.makentoshe.androidgithubcitemplate.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.adapter.PersonsPreparetion_Adapter
import com.makentoshe.androidgithubcitemplate.data.Person_d

class MainActivity_DD : AppCompatActivity() {
    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList:ArrayList<Person_d>
    private val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    private lateinit var adapter: PersonsPreparetion_Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        NoActionBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dd)
        userList = ArrayList()
        addsBtn = findViewById(R.id.addindBth)
        recv = findViewById(R.id.recycler_view_id2)
        addsBtn.setOnClickListener { addInfo() }
        adapter = PersonsPreparetion_Adapter(userList)

        val to_route_plan:Button = findViewById(R.id.button_go_map)
        to_route_plan.setOnClickListener {
            Log.d("TAG", "При нажатии на кнопку должен происходить переход к другой активити.")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun NoActionBar () {
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

    private fun addInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_item,null)

        val userfirstName = v.findViewById<EditText>(R.id.firstName)
        val userlastName = v.findViewById<EditText>(R.id.lastName)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->
            val names = userfirstName.text.toString()
            val lastname = userlastName.text.toString()
            recv.layoutManager = layoutManager
            recv.setHasFixedSize(true)
            recv.adapter = adapter
            userList.add(Person_d(names, lastname))
            adapter.notifyDataSetChanged()
            Toast.makeText(this,"Adding User Information Success",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()

        }
        addDialog.create()
        addDialog.show()
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

}

// #TODO: убрать ActionBar вверху экрана, открывается при onStop, onResume, onPause by Nikita
// #TODO: найти атрибут, отвечающий за перемещение элементов в списке.
// #TODO: разобраться, как работает этот чёртов ChipGroup (как нормально выравнивать элементы?).
// #TODO: сделать RecyclerView для списка участников.
// #TODO: вообще, при нажатии на "Добавить участника" карточка должна переворачиваться (по крайней мере -- реагировать на нажатие).
// #TODO: можно открывать дополнительное мини-окно с вводом информации об участнике.
// #TODO: сделать переход между активити (?)