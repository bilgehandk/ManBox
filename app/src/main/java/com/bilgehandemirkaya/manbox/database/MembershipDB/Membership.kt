package com.bilgehandemirkaya.manbox.database.MembershipDB

import android.provider.SyncStateContract
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bilgehandemirkaya.manbox.util.Constants

@Entity(tableName = Constants.MEMBERSHIPTABLE)
data class Membership(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val username: String,
    val password: String,
    val name: String,
    val surname: String,
    val age: Int,
    val weight: Double,
    val height: Double
)