package com.bilgehandemirkaya.manbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bilgehandemirkaya.manbox.databinding.ActivityMainBinding
import com.bilgehandemirkaya.manbox.databinding.ActivityMenuScreenBinding

class MenuScreen : AppCompatActivity() {
    private lateinit var binding: ActivityMenuScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_screen)

        binding.button12.setOnClickListener{
            val intent = Intent(this, MenuScreen::class.java)
            startActivity(intent)
        }
    }
}