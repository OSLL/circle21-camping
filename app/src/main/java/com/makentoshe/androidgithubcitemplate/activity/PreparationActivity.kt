package com.makentoshe.androidgithubcitemplate.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.adapter.LastPreparationAdapter
import com.makentoshe.androidgithubcitemplate.data.dataPerson
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN as FLAG_FULLSCREEN1

class PreparationActivity : AppCompatActivity() {

    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList:MutableList<dataPerson>
    private val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    private lateinit var adapter: LastPreparationAdapter
    private val toRoatplan:Button = findViewById(R.id.button_go_map)
    private fun NoActionBar () {
        window.setFlags(FLAG_FULLSCREEN1, FLAG_FULLSCREEN1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        NoActionBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dd)
        userList = mutableListOf()
        addsBtn = findViewById(R.id.addindBth)
        recv = findViewById(R.id.recycler_view_id2)

        addsBtn.setOnClickListener {
            addInfo()
        }
        adapter = LastPreparationAdapter(userList)


        toRoatplan.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addInfo() {

        val inflter = LayoutInflater.from(this)
        val inflate = inflter.inflate(R.layout.add_item,null)
        val userfirstName = inflate.findViewById<EditText>(R.id.firstName)
        val userlastName = inflate.findViewById<EditText>(R.id.lastName)
        val addDialog = AlertDialog.Builder(this)
        val names = userfirstName.text.toString()
        val lastname = userlastName.text.toString()

        addDialog.setView(inflate)
        addDialog.setPositiveButton("Ok"){
                dialog,_->
            recv.layoutManager = layoutManager
            recv.setHasFixedSize(true)
            recv.adapter = adapter
            userList.add(dataPerson(names, lastname))
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }

        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
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