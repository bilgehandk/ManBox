package com.bilgehandemirkaya.manbox.database.ActivityDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bilgehandemirkaya.manbox.util.Constants
import java.util.Date

@Entity(tableName = Constants.ACTIVITYTABLE)
class ActivitySystem {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    @ColumnInfo(name = "TraningProgram") var TraningProgram: String,
    @ColumnInfo(name = "date") var date: Date,
    @ColumnInfo(name = "image_resource_id") var imageResourceId: Int)


}
