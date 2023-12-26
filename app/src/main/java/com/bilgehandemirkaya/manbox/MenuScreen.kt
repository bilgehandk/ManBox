package com.bilgehandemirkaya.manbox


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bilgehandemirkaya.manbox.database.LoginDB.Login
import com.bilgehandemirkaya.manbox.database.LoginDB.LoginViewModel
import com.bilgehandemirkaya.manbox.databinding.ActivityMainBinding
import com.bilgehandemirkaya.manbox.databinding.ActivityMenuScreenBinding

class MenuScreen : AppCompatActivity() {
    private lateinit var binding: ActivityMenuScreenBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val nameSurname = intent.getStringExtra("NameSurname")
        binding.textView3.text = "Hello $nameSurname"


        binding.button12.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.button8.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        binding.button6.setOnClickListener{
            val intent = Intent(this, TraningActivity::class.java)
            startActivity(intent)
        }

        binding.button4.setOnClickListener {
            val intent = Intent(this, SalonGiris::class.java)
            startActivity(intent)

        }
    }


}

