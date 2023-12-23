package com.bilgehandemirkaya.manbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bilgehandemirkaya.manbox.databinding.ActivityMainBinding

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}