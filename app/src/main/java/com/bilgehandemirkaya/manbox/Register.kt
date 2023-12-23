package com.bilgehandemirkaya.manbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bilgehandemirkaya.manbox.database.LoginDB.Login
import com.bilgehandemirkaya.manbox.database.LoginDB.LoginViewModel

class Register : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var edUsername: EditText
    private lateinit var edPassword: EditText
    private lateinit var edNameSurname: EditText
    private lateinit var edHeight: EditText
    private lateinit var edWeight: EditText
    private lateinit var edAge: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        edUsername = findViewById(R.id.mailEditText)
        edPassword = findViewById(R.id.editPassword)
        edNameSurname = findViewById(R.id.editNameSurname)
        edHeight = findViewById(R.id.editHeight)
        edWeight = findViewById(R.id.editWeight)
        edAge = findViewById(R.id.EditBirthDate)
        registerButton = findViewById(R.id.RegisterBtn)

        registerButton.setOnClickListener {
            val username = edUsername.text.toString()
            val password = edPassword.text.toString()
            val nameSurname = edNameSurname.text.toString()
            val height = edHeight.text.toString().toIntOrNull()
            val weight = edWeight.text.toString().toIntOrNull()
            val age = edAge.text.toString().toIntOrNull()
            var i = 10

            if (username.isNotEmpty() && password.isNotEmpty() && nameSurname.isNotEmpty() && height != null && weight != null && age != null) {
                val newLogin = Login(
                    username_mail = username,
                    user_id = i,
                    password = password,
                    name_surname = nameSurname,
                    height = height,
                    weight = weight,
                    age = age
                )
                i++
                loginViewModel.insertLogin(newLogin)

                // Otomatik olarak giriş yapma işlemi
                loginViewModel.loginUser(username, password).observe(this, Observer { loggedInUser ->
                    if (loggedInUser != null) {
                        val intent = Intent(this@Register, MainActivity::class.java)
                        startActivity(intent)
                        finish() // Register aktivitesini kapat
                    } else {
                        // Handle unsuccessful login
                        Toast.makeText(this@Register, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this@Register, "Please fill in all fields with valid information", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
