package com.bilgehandemirkaya.manbox


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


        // Assuming you have ViewModelProvider in your application
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)




        // Insert the newLogin into the database using the ViewModel
        loginViewModel.performLogin()

        binding.button.setOnClickListener {
            val mailText = binding.mailEnterance.text.toString()
            val password = binding.passwordEnterance.text.toString()

            // Call the loginUser function from LoginViewModel
            loginViewModel.loginUser(mailText, password).observe(this, Observer { loggedInUser ->
                // Check if loggedInUser is not null, indicating successful login
                if (loggedInUser != null) {
                    // Open the MenuScreen activity
                    val intent = Intent(this@MainActivity, MenuScreen::class.java)
                    startActivity(intent)
                } else {
                    // Handle unsuccessful login (e.g., show an error message)
                    // You can customize this part based on your app's requirements
                    // For example, you can display a toast or set an error message on the UI
                    // indicating that the login credentials are incorrect.
                }
            })
        }
    }
}
