package com.bilgehandemirkaya.manbox.database.MembershipDB

import android.provider.SyncStateContract
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bilgehandemirkaya.manbox.util.Constants

@Dao
interface MembershipDao {

        @Query("SELECT * FROM ${Constants.MEMBERSHIPTABLE}")
        fun getAllMemberships(): LiveData<List<Membership>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(membership: Membership)

        @Query("DELETE FROM ${Constants.MEMBERSHIPTABLE}")
        suspend fun deleteAll()



        @Query("SELECT sizeClass FROM ${Constants.MEMBERSHIPTABLE} WHERE id_class = :id")
        suspend fun getLatestMembershipNumber(id: Int): Int

        @Query("SELECT * FROM ${Constants.MEMBERSHIPTABLE} WHERE username_mail = :username")
        suspend fun getMembership(username: String): Membership

        @Query("UPDATE ${Constants.MEMBERSHIPTABLE} SET sizeClass = :size WHERE username_mail = :username")
        suspend fun updateMembership(username: String, size: Int)

        @Query("DELETE FROM ${Constants.MEMBERSHIPTABLE} WHERE username_mail = :username")
        suspend fun deleteMembership(username: String)


}
