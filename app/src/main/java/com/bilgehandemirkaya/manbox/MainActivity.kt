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
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.bilgehandemirkaya.manbox.WorkManager.MyWorker
import com.bilgehandemirkaya.manbox.databinding.ActivityMainBinding
import com.bilgehandemirkaya.manbox.database.LoginDB.LoginViewModel
import java.util.concurrent.TimeUnit

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

        val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setInitialDelay(1, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.performLogin()

        binding.button.setOnClickListener {
            val mailText = binding.mailEnterance.text.toString()
            val password = binding.passwordEnterance.text.toString()

            if (mailText.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel.loginUser(mailText, password).observe(this, Observer { loggedInUser ->
                    if (loggedInUser != null) {
                        loginViewModel.changeEntranceStatus(loggedInUser.username_mail, true)
                            .observe(this, Observer { success ->
                                if (success) {
                                    // Open the MenuScreen activity
                                    val intent = Intent(this@MainActivity, MenuScreen::class.java)
                                    intent.putExtra("userName", loggedInUser?.username_mail)
                                    startActivity(intent)
                                } else {
                                    // Handle unsuccessful entrance status change
                                    Toast.makeText(this@MainActivity, "Failed to change entrance status", Toast.LENGTH_SHORT).show()
                                }
                            })


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
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, R.raw.sound1) // Recreate the MediaPlayer for future use
        isPlaying = false
        Toast.makeText(this@MainActivity, "Music stopped", Toast.LENGTH_SHORT).show()
    }



    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }



}
