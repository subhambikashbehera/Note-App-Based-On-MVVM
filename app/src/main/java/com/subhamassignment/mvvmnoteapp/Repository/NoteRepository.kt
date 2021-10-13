package com.subhamassignment.mvvmnoteapp.Repository

import androidx.lifecycle.LiveData
import com.subhamassignment.mvvmnoteapp.database.Dao
import com.subhamassignment.mvvmnoteapp.database.Table

class NoteRepository(var notesdao:Dao) {

    val allnotes:LiveData<List<Table>> =notesdao.getallnotes()

    suspend fun insert(note:Table){
        notesdao.inserts(note)
    }

    suspend fun update(note:Table){
        notesdao.updates(note)
    }

    suspend fun delete(notes:Long){
        notesdao.deletes(notes)
    }



}