package com.bilgehandemirkaya.manbox.database.MembershipDB

import android.provider.SyncStateContract
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.bilgehandemirkaya.manbox.database.LoginDB.Login
import com.bilgehandemirkaya.manbox.util.Constants

@Entity(tableName = Constants.MEMBERSHIPTABLE)
data class Membership(
<<<<<<< Updated upstream
    @PrimaryKey(autoGenerate = true)
    var id_class: Int,
    var username_mail: String,
    var id_activity: Int,
    var day: String,
    var hour: String,
    var name_surname: String,
    var teacher_name: String,
)
=======
    @PrimaryKey(autoGenerate = true) val id: Int,
    
    val username: String,
    val password: String,
    val name: String,
    val surname: String,
    val age: Int,
    val weight: Double,
    val height: Double
)
>>>>>>> Stashed changes
