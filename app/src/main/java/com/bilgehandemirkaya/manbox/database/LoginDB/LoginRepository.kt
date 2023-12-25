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

    fun loginUser(username: String, password: String): LiveData<Login?> {
        return loginDao.getLoginByUsernameAndPassword(username, password)
    }


    fun getLoginsByUserId(userId: Int): LiveData<List<Login>> {
        return loginDao.getLoginsByUserId(userId)
    }

    fun getLastLogin(): Login {
        return loginDao.getEntranceUser()
    }


    fun changeEntranceStatus(username: String, entrance: Boolean) {
        loginDao.changeEntranceStatus(username, entrance)

    }
}
