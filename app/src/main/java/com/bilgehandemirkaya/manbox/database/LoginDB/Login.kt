package com.bilgehandemirkaya.manbox.database.LoginDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bilgehandemirkaya.manbox.util.Constants

@Entity(tableName = Constants.LOGINTABLE)
data class Login(
    @PrimaryKey(autoGenerate = true)
    var username_mail: String,
    var user_id: Int, // 1==manager, 2==user
    var password: String,
    var name_surname: String,
    var height: Int,
    var weight: Int,
    var age: Int
) {
    override fun toString(): String {
        return "Login{" +
                "username_mail='" + username_mail + '\'' +
                ", user_id=" + user_id +
                ", password='" + password + '\'' +
                ", name_surname='" + name_surname + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                '}'
    }
}
