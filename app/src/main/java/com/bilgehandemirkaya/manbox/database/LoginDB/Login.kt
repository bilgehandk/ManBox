package com.bilgehandemirkaya.manbox.database.LoginDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bilgehandemirkaya.manbox.util.Constants

@Entity(tableName = Constants.LOGINTABLE)
data class Login(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val userId: Int,
    val activityId: Int,
    val day: String,
    val time: String,
    val name: String,
    val surname: String
)