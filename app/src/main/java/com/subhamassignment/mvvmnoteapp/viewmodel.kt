package com.subhamassignment.mvvmnoteapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.subhamassignment.mvvmnoteapp.Repository.NoteRepository
import com.subhamassignment.mvvmnoteapp.database.dbManipulation
import com.subhamassignment.mvvmnoteapp.database.Table
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class viewmodel(application: Application):AndroidViewModel(application) {

    val allnotes:LiveData<List<Table>>
    val repository:NoteRepository

    init {
        val dao=dbManipulation.getinstance(application).Daoa()
        repository=NoteRepository(dao)
        allnotes=repository.allnotes
    }

  suspend  fun deletenotes(note:Long)=viewModelScope.launch(Dispatchers.IO) {
      try {
          repository.delete(note)
      }catch (e:Exception)
      {
          e.printStackTrace()
      }



  }

    suspend fun insertnotes(note:Table)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    suspend  fun updatenotes(note:Table)=viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }




}