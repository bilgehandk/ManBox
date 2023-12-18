package com.bilgehandemirkaya.manbox.database.ActivityDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.bilgehandemirkaya.manbox.util.Constants


@Entity(tableName = Constants.ACTIVITYTABLE)
class ActivitySystem(
    @PrimaryKey(autoGenerate = true)
    var id_activity: Int = 0,
    @ColumnInfo(name = "ActivityName") var activityName: String,
    @ColumnInfo(name = "date") var dateActivity: String,
    @ColumnInfo(name = "clock") var clockActivity: String, // Changed the column name to "clock"
    @ColumnInfo(name = "trainerName") var trainerName: String) // Changed the column name to "clock"
{
    override fun toString(): String {
        return "ActivitySystem{" +
                "id=" + id_activity +
                ", activityName='" + activityName + '\'' +
                ", dateActivity='" + dateActivity + '\'' +
                ", clockActivity='" + clockActivity + '\'' +
                ", clockActivity='" + trainerName + '\'' +
                '}'
    }
}

