package com.bilgehandemirkaya.manbox.database.LoginDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bilgehandemirkaya.manbox.database.ActivityDB.ActivitySystemRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Login>>
    private val repository: LoginRepository

    init {
        val loginDao = LoginDatabase.getDatabase(application).loginDao()
        repository = LoginRepository(loginDao)
        readAllData = repository.readAllData
    }

    fun insertLogin(login: Login) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertLogin(login)
        }
    }

    fun insertLogins(logins: List<Login>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertLogins(logins)
        }
    }

    fun updateLogin(login: Login) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLogin(login)
        }
    }

    fun deleteLogin(login: Login) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLogin(login)
        }
    }

    fun deleteAllLogins() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllLogins()
        }
    }

    fun loginUser(username: String, password: String): LiveData<Login?> {
        return repository.loginUser(username, password)
    }


    fun getLoginsByUserId(userId: Int): LiveData<List<Login>> {
        return repository.getLoginsByUserId(userId)
    }


    fun getCurrentUser(): LiveData<List<Login>> {
        return readAllData
    }


    fun performLogin() {
        val newLogin = Login(
            username_mail = "bilgehan@gmail.com",
            user_id = 2,
            password = "123456",
            name_surname = "Bilgehan Demirkaya",
            height = 175,
            weight = 70,
            age = 25
        )
        insertLogin(newLogin)
    }

}
