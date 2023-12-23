package com.bilgehandemirkaya.manbox

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bilgehandemirkaya.manbox.database.LoginDB.Login
import com.bilgehandemirkaya.manbox.databinding.ActivityMainBinding
import com.bilgehandemirkaya.manbox.database.LoginDB.LoginViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying: Boolean = false
    private var doubleClick = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.performLogin()
        insertSampleUsers()

        binding.button.setOnClickListener {
            val mailText = binding.mailEnterance.text.toString()
            val password = binding.passwordEnterance.text.toString()

            if (mailText.isNotEmpty() && password.isNotEmpty()) {
                // Call the loginUser function from LoginViewModel
                loginViewModel.loginUser(mailText, password).observe(this, Observer { loggedInUser ->
                    if (loggedInUser != null) {
                        // Open the MenuScreen activity
                        val intent = Intent(this@MainActivity, MenuScreen::class.java)
                        intent.putExtra("NameSurname", loggedInUser?.name_surname)
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

        mediaPlayer = MediaPlayer.create(this, R.raw.sound1)

        binding.root.setOnClickListener {
            if (doubleClick) {
                stopMusic()
            } else {
                doubleClick = true
                Handler(Looper.getMainLooper()).postDelayed({
                    doubleClick = false
                }, 500)
                toggleMusic()
            }
        }
    }

    private fun toggleMusic() {
        if (isPlaying) {
            pauseMusic()
        } else {
            startMusic()
        }
    }

    private fun startMusic() {
        mediaPlayer?.start()
        isPlaying = true
        Toast.makeText(this@MainActivity, "Music started", Toast.LENGTH_SHORT).show()
    }

    private fun pauseMusic() {
        mediaPlayer?.pause()
        isPlaying = false
        Toast.makeText(this@MainActivity, "Music paused", Toast.LENGTH_SHORT).show()
    }

    private fun stopMusic() {
        mediaPlayer?.reset()
        mediaPlayer?.prepare()
        isPlaying = false
        Toast.makeText(this@MainActivity, "Music stopped", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun insertSampleUsers() {
        val arman = Login(
            username_mail = "arman@gmail.com",
            user_id = 1,
            password = "arman123",
            name_surname = "Arman Yılmazkurt",
            height = 180,
            weight = 75,
            age = 20
        )

        val emirhan = Login(
            username_mail = "emirhan@gmail.com",
            user_id = 2,
            password = "emirhan123",
            name_surname = "Emirhan Kaya",
            height = 185,
            weight = 70,
            age = 28
        )

        val simay = Login(
            username_mail = "simay@gmail.com",
            user_id = 3,
            password = "simay123",
            name_surname = "Simay Ardıç",
            height = 165,
            weight = 55,
            age = 25
        )

        // Insert users into the database
        loginViewModel.insertLogin(arman)
        loginViewModel.insertLogin(emirhan)
        loginViewModel.insertLogin(simay)
    }

}
