package com.bilgehandemirkaya.manbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val registerButton = findViewById<Button>(R.id.RegisterBtn)

        registerButton.setOnClickListener {
            val intent = Intent(this@Register, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
