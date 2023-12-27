package com.bilgehandemirkaya.manbox.database.LoginDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bilgehandemirkaya.manbox.util.Constants

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLogin(login: Login)

    @Update
    fun updateLogin(login: Login)

    @Delete
    fun deleteLogin(login: Login)

    @Query("DELETE FROM ${Constants.LOGINTABLE}")
    fun deleteAllLogins()

    @Query("SELECT * FROM ${Constants.LOGINTABLE} ORDER BY username_mail ASC") // Sıralama alanını değiştirdik
    fun getAllLogins(): LiveData<List<Login>>

    @Query("SELECT * FROM ${Constants.LOGINTABLE} WHERE username_mail = :username_mail")
    fun getLoginByUsername(username_mail: String): Login

    @Query("SELECT * FROM ${Constants.LOGINTABLE} WHERE user_id = :user_id")
    fun getLoginsByUserId(user_id: Int): LiveData<List<Login>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLogins(logins: List<Login>)

    // LoginDao.kt

    @Query("UPDATE ${Constants.LOGINTABLE} SET password = :newPassword WHERE username_mail = :username")
    suspend fun changePassword(username: String, newPassword: String)

    @Query("SELECT * FROM ${Constants.LOGINTABLE} WHERE username_mail = :username AND password = :password")
    fun getLoginByUsernameAndPassword(username: String, password: String): LiveData<Login?>

    // entrance true olan kullanıcıyı getirir
    @Query("SELECT * FROM ${Constants.LOGINTABLE} WHERE entrance = 1")
    fun getLastUser(): LiveData<Login?>

    // entrance durumunu değiştirir
    @Query("UPDATE ${Constants.LOGINTABLE} SET entrance = :entrance WHERE username_mail = :username")
    suspend fun changeEntranceStatus(username: String, entrance: Boolean): Int



}
