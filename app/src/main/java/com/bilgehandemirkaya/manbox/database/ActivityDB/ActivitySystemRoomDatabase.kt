package com.bilgehandemirkaya.manbox.database.ActivityDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//If you change anything on the database like adding a field to table, chnaging type of a filed, deleting a filed, changing the name of the field
@Database(entities = [ActivitySystem::class], version = 5)
abstract class OperatingSystemsRoomDatabase : RoomDatabase() {
    abstract fun operatingSystemDao(): OperatingSystemDAO

    companion object {
        private const val DATABASE_NAME = "operating_systems_database"

        @Volatile
        private var INSTANCE: OperatingSystemsRoomDatabase? = null

        fun getDatabase(context: Context): OperatingSystemsRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OperatingSystemsRoomDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }


    }
}
