package com.bilgehandemirkaya.manbox.Fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.bilgehandemirkaya.manbox.R
import com.bilgehandemirkaya.manbox.database.LoginDB.LoginViewModel

class ChangePasswordDialogFragment : DialogFragment() {

    private lateinit var newPasswordEditText: EditText
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_change_password, null)

        newPasswordEditText = view.findViewById(R.id.newPasswordEditText)

        builder.setView(view)
            .setTitle("Change Password")
            .setPositiveButton("Change") { _, _ ->
                val newPassword = newPasswordEditText.text.toString()
                val username = loginViewModel.lastUser.value?.username_mail

                if (username != null && newPassword.isNotEmpty()) {
                    loginViewModel.changePassword(username, newPassword)
                } else {
                    Toast.makeText(requireContext(), "Please enter new password", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel") { _, _ ->
                dialog?.cancel()
            }

        return builder.create()
    }
}


