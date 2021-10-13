package com.subhamassignment.mvvmnoteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Table::class],version = 9,exportSchema = false)
abstract class dbManipulation:RoomDatabase() {
    abstract fun Daoa():Dao
    companion object{
        @Volatile
        private var instance:dbManipulation?=null
        fun getinstance(context:Context):dbManipulation{
            if (instance == null){
                synchronized(this){
                    instance=Room.databaseBuilder(context.applicationContext,
                        dbManipulation::class.java,"NOTEDB").fallbackToDestructiveMigration().build()
                }
            }
        return instance!!
        }
    }
}