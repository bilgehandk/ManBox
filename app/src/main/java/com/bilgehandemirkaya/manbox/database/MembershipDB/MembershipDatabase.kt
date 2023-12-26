package com.bilgehandemirkaya.manbox.database.MembershipDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Membership::class], version = 3, exportSchema = false)
abstract class MembershipDatabase : RoomDatabase() {

    abstract fun membershipDao(): MembershipDao

    companion object {
        @Volatile
        private var INSTANCE: MembershipDatabase? = null

        fun getDatabase(context: Context): MembershipDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MembershipDatabase::class.java,
                    "membership_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
