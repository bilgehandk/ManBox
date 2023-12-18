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

    @Query("SELECT * FROM ${Constants.LOGINTABLE} ORDER BY id ASC")
    fun getAllLogins(): LiveData<List<Login>>

    @Query("SELECT * FROM ${Constants.LOGINTABLE} WHERE id = :id")
    fun getLoginById(id: Int): Login

    @Query("SELECT * FROM ${Constants.LOGINTABLE} WHERE userId = :userId")
    fun getLoginsByUserId(userId: Int): LiveData<List<Login>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLogins(logins: List<Login>)
}