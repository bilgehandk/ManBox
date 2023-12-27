package com.bilgehandemirkaya.manbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bilgehandemirkaya.manbox.Fragment.ChangePasswordDialogFragment
import com.bilgehandemirkaya.manbox.database.LoginDB.Login
import com.bilgehandemirkaya.manbox.database.LoginDB.LoginViewModel
import com.bilgehandemirkaya.manbox.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        setContentView(R.layout.activity_account)

        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button20.setOnClickListener{
            showUserInfoDialog()
        }
    }


    private fun showUserInfoDialog() {
        val currentUserLiveData = loginViewModel.getCurrentUser()

        currentUserLiveData.observe(this, Observer { userList ->
            if (userList.isNotEmpty()) {
                val currentUser = userList[0]
                showCustomDialog(currentUser)
            } else {
                Toast.makeText(this, "User information not available", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showCustomDialog(currentUser: Login) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
        val nameSurnameTextView = dialogView.findViewById<TextView>(R.id.nameSurnameTextView)
        val heightTextView = dialogView.findViewById<TextView>(R.id.heightTextView)
        val weightTextView = dialogView.findViewById<TextView>(R.id.weightTextView)
        val ageTextView = dialogView.findViewById<TextView>(R.id.ageTextView)

        nameSurnameTextView.text = "Name: ${currentUser.name_surname}"
        heightTextView.text = "Height: ${currentUser.height} cm"
        weightTextView.text = "Weight: ${currentUser.weight} kg"
        ageTextView.text = "Age: ${currentUser.age} years"

        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
            .setTitle("User Information")
            .setPositiveButton("Change Password") { _, _ ->
                val changePasswordDialogFragment = ChangePasswordDialogFragment()
                changePasswordDialogFragment.show(supportFragmentManager, "ChangePasswordDialogFragment")
            }
            .setNegativeButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }

}