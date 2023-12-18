package com.bilgehandemirkaya.manbox.database.LoginDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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

    suspend fun getLoginById(id: Int): Login {
        return repository.getLoginById(id)
    }

    fun getLoginsByUserId(userId: Int): LiveData<List<Login>> {
        return repository.getLoginsByUserId(userId)
    }
}
