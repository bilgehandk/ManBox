package com.bilgehandemirkaya.manbox.database.LoginDB

import androidx.lifecycle.LiveData

class LoginRepository(private val loginDao: LoginDao) {
    val readAllData: LiveData<List<Login>> = loginDao.getAllLogins()

    fun insertLogin(login: Login) {
        loginDao.insertLogin(login)
    }

    fun insertLogins(logins: List<Login>) {
        loginDao.insertAllLogins(logins)
    }

    fun updateLogin(login: Login) {
        loginDao.updateLogin(login)
    }

    fun deleteLogin(login: Login) {
        loginDao.deleteLogin(login)
    }

    fun deleteAllLogins() {
        loginDao.deleteAllLogins()
    }

    fun getAllLogins(): LiveData<List<Login>> {
        return loginDao.getAllLogins()
    }

    fun getLoginById(id: Int): Login {
        return loginDao.getLoginById(id)
    }

    fun getLoginsByUserId(userId: Int): LiveData<List<Login>> {
        return loginDao.getLoginsByUserId(userId)
    }
}