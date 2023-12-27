package com.bilgehandemirkaya.manbox.database.ActivityDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bilgehandemirkaya.manbox.util.Constants

//If you change anything on the database like adding a field to table, chnaging type of a filed, deleting a filed, changing the name of the field
@Database(entities = [ActivitySystem::class], version = 5)
abstract class ActivitySystemRoomDatabase : RoomDatabase() {
    abstract fun ActivitySystemDAO(): ActivitySystemDAO

    companion object {


        @Volatile
        private var INSTANCE: ActivitySystemRoomDatabase? = null

        fun getDatabase(context: Context): ActivitySystemRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ActivitySystemRoomDatabase::class.java,
                    Constants.ACTIVITYTABLE
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }


    }
}
