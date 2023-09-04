package com.lich.minskattractions

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase




@Database(entities = [Item::class], version = 4)
abstract class MainDB:RoomDatabase() {
    abstract fun getDao():Dao

    companion object {
        fun getDb(context:Context):MainDB{

            val rdc: Callback = object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    // do something after database has been created

                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    // do something every time database is open
                }
            }

            return Room.databaseBuilder(
                context.applicationContext,
                MainDB::class.java,
                "attractions.db")
                .addCallback(rdc)
                .fallbackToDestructiveMigration()
                .build()


        }

    }


}