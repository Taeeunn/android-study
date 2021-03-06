package com.example.floatingactionbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isMenuOpen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menu.setOnClickListener {
            menuOpen()
        }
    }

    fun menuOpen(){
        when(isMenuOpen){
            false ->{
                setting.animate().translationY(-resources.getDimension(R.dimen.setting))
                add.animate().translationY(-resources.getDimension(R.dimen.add))
                people.animate().translationY(-resources.getDimension(R.dimen.people))

                isMenuOpen=true
            }

            true ->{
                setting.animate().translationY(0F)
                add.animate().translationY(0F)
                people.animate().translationY(0F)

                isMenuOpen=false
            }
        }
    }
}