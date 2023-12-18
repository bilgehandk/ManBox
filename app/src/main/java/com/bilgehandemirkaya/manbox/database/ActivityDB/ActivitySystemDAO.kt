package com.bilgehandemirkaya.manbox.database.ActivityDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bilgehandemirkaya.manbox.util.Constants



@Dao
interface ActivitySystemDAO {
    // The conflict strategy defines what happens,if there is an existing entry.
    // The default action is ABORT.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActivitySystem(activitySystems: ActivitySystem)

    @Update
    fun updateActivitySystem(activitySystems: ActivitySystem)

    @Delete
    fun deleteActivitySystem(activitySystems: ActivitySystem)

    @Query("DELETE FROM ${Constants.ACTIVITYTABLE}")
    fun deleteAllActivitySystems()

    @Query("SELECT * FROM ${Constants.ACTIVITYTABLE} ORDER BY id DESC")
    fun getAllActivitySystems():LiveData<List<ActivitySystem>>

    @Query("SELECT * FROM ${Constants.ACTIVITYTABLE} WHERE id =:id")
    fun getActivitySystemById(id:Int):ActivitySystem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllOperatingSystems(operatingSystems: ArrayList<ActivitySystem>){
        operatingSystems.forEach{
            insertActivitySystem(it)
        }
    }

}
