package com.bilgehandemirkaya.manbox.database.MembershipDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bilgehandemirkaya.manbox.util.Constants

@Database(entities = [Membership::class], version = 2, exportSchema = false)
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
                    Constants.MEMBERSHIPTABLE
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
