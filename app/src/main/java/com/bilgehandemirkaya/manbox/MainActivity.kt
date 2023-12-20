package com.bilgehandemirkaya.manbox


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bilgehandemirkaya.manbox.MenuScreen
import com.bilgehandemirkaya.manbox.database.LoginDB.Login
import com.bilgehandemirkaya.manbox.databinding.ActivityMainBinding
import com.bilgehandemirkaya.manbox.database.LoginDB.LoginViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginViewModel.performLogin()

        binding.button.setOnClickListener {
            val mailText = binding.mailEnterance.text.toString()
            val password = binding.passwordEnterance.text.toString()

            if (mailText.isNotEmpty() && password.isNotEmpty()) {
                // Call the loginUser function from LoginViewModel
                loginViewModel.loginUser(mailText, password).observe(this, Observer { loggedInUser ->
                    if (loggedInUser != null) {
                        // Open the MenuScreen activity
                        val intent = Intent(this@MainActivity, MenuScreen::class.java)
                        startActivity(intent)
                    } else {
                        // Handle unsuccessful login
                        Toast.makeText(this@MainActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this@MainActivity, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.button3.setOnClickListener{
            val intent = Intent(this@MainActivity, Register::class.java)
            startActivity(intent)
        }

    }
}
