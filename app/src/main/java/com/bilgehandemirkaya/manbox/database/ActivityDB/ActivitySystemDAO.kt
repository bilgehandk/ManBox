package com.bilgehandemirkaya.manbox.database.ActivityDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface OperatingSystemDAO {
    // The conflict strategy defines what happens,if there is an existing entry.
    // The default action is ABORT.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOperatingSystem(operatingSystems: OperatingSystems)

    @Update
    fun updateOperatingSystem(operatingSystems: OperatingSystems)

    @Delete
    fun deleteOperatingSystem(operatingSystems: OperatingSystems)

    @Query("DELETE FROM ${Constants.TABLENAME}")
    fun deleteAllOperatingSystems()

    @Query("SELECT * FROM ${Constants.TABLENAME} ORDER BY id DESC")
    fun getAllOperatingSystems():LiveData<List<OperatingSystems>>

    @Query("SELECT * FROM ${Constants.TABLENAME} WHERE id =:id")
    fun getOperatingSystemById(id:Int):OperatingSystems

    @Query("SELECT * FROM ${Constants.TABLENAME} WHERE productName LIKE :name")
    fun getOperatingSystemByName(name:String):MutableList<OperatingSystems>

    @Query("SELECT * FROM ${Constants.TABLENAME} WHERE productName LIKE :searchKey OR date LIKE :searchKey")
    fun getOperatingSystemsBySearchKey(searchKey:String): Flow<List<OperatingSystems>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllOperatingSystems(operatingSystems: ArrayList<OperatingSystems>){
        operatingSystems.forEach{
            insertOperatingSystem(it)
        }
    }

}
