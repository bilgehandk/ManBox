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

    @Query("DELETE FROM ${Constants.MEMBERSHIPTABLE}")
    fun deleteAllMemberships()

    @Query("SELECT * FROM ${Constants.MEMBERSHIPTABLE} ORDER BY id_class ASC")
    fun getAllMemberships(): LiveData<List<Membership>>

    @Query("SELECT * FROM ${Constants.MEMBERSHIPTABLE} WHERE id_class = :id_class")
    fun getMembershipById(id_class: Int): Membership

    @Query("SELECT * FROM ${Constants.MEMBERSHIPTABLE} WHERE username_mail LIKE :searchKey OR name_surname LIKE :searchKey")
    fun getMembershipsBySearchKey(searchKey: String): LiveData<List<Membership>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMemberships(memberships: List<Membership>)

    @Query("SELECT sizeClass FROM ${Constants.MEMBERSHIPTABLE} WHERE id_class = :id_class")
    fun getLatestMembershipNumber(id_class: Int): Int

    @Query("SELECT * FROM ${Constants.MEMBERSHIPTABLE} WHERE id_activity = :id_activity")
    fun getMembershipByActivityId(id_activity: Int): Membership
}
