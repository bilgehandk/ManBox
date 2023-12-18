package com.bilgehandemirkaya.manbox.database.MembershipDB

import android.content.Context
import android.provider.SyncStateContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bilgehandemirkaya.manbox.database.ActivityDB.OperatingSystemsRoomDatabase

@Database(entities = [Membership::class], version = 1, exportSchema = false)
abstract class MembershipDatabase : RoomDatabase() {
    abstract fun membershipDao(): MembershipDao

    companion object {
        @Volatile
        private var INSTANCE: MembershipDatabase? = null

        fun getDatabase(context: Context): MembershipDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MembershipDatabase::class.java,
                    SyncStateContract.Constants.DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}