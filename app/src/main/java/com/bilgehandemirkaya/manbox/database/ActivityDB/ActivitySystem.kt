package com.bilgehandemirkaya.manbox.database.ActivityDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.bilgehandemirkaya.manbox.util.Constants
import java.util.Date


@Entity(tableName = Constants.ACTIVITYTABLE)
class OperatingSystems(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    @ColumnInfo(name = "ActivityName") var activityName: String,
    @ColumnInfo(name = "date") var dateActivity: Date,
    @ColumnInfo(name = "date") var clockActivity: String,
    @ColumnInfo(name = "image_resource_id") var imageResourceId: Int)
{
    override fun toString(): String {
        return "Customer{" +
                "id=" + id +
                ", productName='" + activityName + '\'' +
                ", price='" + dateActivity + '\'' +
                ", date=" + clockActivity +
                '}'
    }
}
