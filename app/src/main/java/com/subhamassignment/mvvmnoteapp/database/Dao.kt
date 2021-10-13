package com.subhamassignment.mvvmnoteapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun inserts(table: Table)

    @Update
     fun updates(table: Table)

    @Query("DELETE FROM nt WHERE id = :ID")
     fun deletes(ID:Long?)

    @Query("select * from nt order by id desc")
    fun getallnotes(): LiveData<List<Table>>

}