package com.makentoshe.androidgithubcitemplate.activity



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.databinding.ActivityFirstMainActiviteBinding


class MainActivity_DD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityFirstMainActiviteBinding>(
            this,
            R.layout.activity_first_main_activite
        )
    }
}









// #TODO: убрать ActionBar вверху экрана, открывается при onStop, onResume, onPause by Nikita
// #TODO: найти атрибут, отвечающий за перемещение элементов в списке.
// #TODO: разобраться, как работает этот чёртов ChipGroup (как нормально выравнивать элементы?).
// #TODO: сделать RecyclerView для списка участников.
// #TODO: вообще, при нажатии на "Добавить участника" карточка должна переворачиваться (по крайней мере -- реагировать на нажатие).
// #TODO: можно открывать дополнительное мини-окно с вводом информации об участнике.
// #TODO: сделать переход между активити (?)