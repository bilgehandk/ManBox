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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMembership(membership: Membership)

    @Update
    fun updateMembership(membership: Membership)

    @Delete
    fun deleteMembership(membership: Membership)

    @Query("DELETE FROM ${SyncStateContract.Constants.MEMBERSHIPTABLE}")
    fun deleteAllMemberships()

    @Query("SELECT * FROM ${Constants.MEMBERSHIPTABLE} ORDER BY id ASC")
    fun getAllMemberships(): LiveData<List<Membership>>

    @Query("SELECT * FROM ${Constants.MEMBERSHIPTABLE WHERE id = :id")
    fun getMembershipById(id: Int): Membership

    @Query("SELECT * FROM ${Constants.MEMBERSHIP_TABLE_NAME} WHERE username LIKE :searchKey OR name LIKE :searchKey OR surname LIKE :searchKey")
    fun getMembershipsBySearchKey(searchKey: String): LiveData<List<Membership>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMemberships(memberships: List<Membership>)
}