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

    fun changeEntranceStatus(username: String, entrance: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeEntranceStatus(username, entrance)
        }
    }


    fun getCurrentUser(): LiveData<List<Login>> {
        return readAllData
    }

    fun getLastUser(): Login {
        return repository.getLastLogin()
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
            user_id = 3,
            password = "emirhan123",
            name_surname = "Emirhan Kaya",
            height = 185,
            weight = 70,
            age = 28
        )

        val simay = Login(
            username_mail = "simay@gmail.com",
            user_id = 4,
            password = "simay123",
            name_surname = "Simay Ardıç",
            height = 165,
            weight = 55,
            age = 25
        )

        // Insert users into the database
        insertLogin(arman)
        insertLogin(emirhan)
        insertLogin(simay)
        insertLogin(newLogin)
    }

}
