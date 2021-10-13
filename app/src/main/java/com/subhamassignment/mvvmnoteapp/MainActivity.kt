package com.subhamassignment.mvvmnoteapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView.MultiChoiceModeListener
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.subhamassignment.mvvmnoteapp.database.Table
import com.subhamassignment.mvvmnoteapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

   lateinit var binding:ActivityMainBinding
   lateinit var adapterx: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        supportActionBar?.hide()


        val viewmodel= ViewModelProvider(this)[viewmodel::class.java]

        viewmodel.allnotes.observe(this,{note->
            adapterx= Adapter(this, note as ArrayList<Table>)
            binding.recyclerView.adapter=adapterx
            val lm=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            binding.recyclerView.layoutManager=lm
        })




        binding.addnotes.setOnClickListener {
            val popup=MaterialAlertDialogBuilder(this)
            var alertDialog:AlertDialog?=null
            val viewpop=layoutInflater.inflate(R.layout.addnote,null)
            val notes=viewpop.findViewById<EditText>(R.id.addTitle)
            val savenote=viewpop.findViewById<Button>(R.id.btnAdd)
            popup.background=ColorDrawable(Color.TRANSPARENT)
            popup.setView(viewpop).create()
            alertDialog?.setCancelable(false)
            alertDialog=popup.show()

            savenote.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh.mm.ss")
                    val formatter2 = DateTimeFormatter.ofPattern("dd/MM/YYYY")
                    viewmodel.insertnotes(Table(null,notes.text.toString(),LocalTime.now().format(formatter).toString(),LocalDate.now().format(formatter2)))
                    alertDialog.dismiss()
                }
            }
        }
        binding.lifecycleOwner=this














    }

}