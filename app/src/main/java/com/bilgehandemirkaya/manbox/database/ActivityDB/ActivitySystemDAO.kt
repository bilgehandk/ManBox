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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActivitySystem(activitySystem: ActivitySystem)

    @Update
    fun updateActivitySystem(activitySystem: ActivitySystem)

    @Delete
    fun deleteActivitySystem(activitySystem: ActivitySystem)

    @Query("DELETE FROM ${Constants.ACTIVITYTABLE}")
    fun deleteAllActivitySystems()

    @Query("SELECT * FROM ${Constants.ACTIVITYTABLE} ORDER BY id_activity DESC")
    fun getAllActivitySystems(): LiveData<List<ActivitySystem>>

    @Query("SELECT * FROM ${Constants.ACTIVITYTABLE} WHERE id_activity = :id_activity")
    fun getActivitySystemById(id_activity: Int): LiveData<ActivitySystem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllActivitySystems(activitySystems: List<ActivitySystem>)

    @Query("SELECT * FROM ${Constants.ACTIVITYTABLE} WHERE date = :date")
    fun getActivityByDate(date: String): LiveData<List<ActivitySystem>>

}
