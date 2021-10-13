package com.subhamassignment.mvvmnoteapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.subhamassignment.mvvmnoteapp.database.Table
import com.subhamassignment.mvvmnoteapp.database.dbManipulation
import com.subhamassignment.mvvmnoteapp.databinding.ItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Adapter(var context: Context, var listdata: ArrayList<Table>) :
    RecyclerView.Adapter<Adapter.Viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

        val binding: ItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item,
            parent,
            false
        )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val notes = listdata[position]
        holder.bind(notes)

        when ((0..7).random()) {
            1 -> {
                holder.binding.popupss.setCardBackgroundColor(Color.parseColor("#E7E7E7"))
            }
            2 -> {
                holder.binding.popupss.setCardBackgroundColor(Color.parseColor("#FFCECE"))
            }
            3 -> {
                holder.binding.popupss.setCardBackgroundColor(Color.parseColor("#EDCEFC"))
            }
            4 -> {
                holder.binding.popupss.setCardBackgroundColor(Color.parseColor("#C7FEF6"))
            }
            5 -> {
                holder.binding.popupss.setCardBackgroundColor(Color.parseColor("#FFF0D0"))
            }
            6 -> {
                holder.binding.popupss.setCardBackgroundColor(Color.parseColor("#E7E7E7"))
            }
            7 -> {
                holder.binding.popupss.setCardBackgroundColor(Color.parseColor("#E7E7E7"))
            }
        }

        holder.binding.option.setOnClickListener {
            val popupMenu = PopupMenu(context, holder.binding.option)
            popupMenu.inflate(R.menu.popup_menu)

            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.delete_id -> {
                        CoroutineScope(Dispatchers.IO).launch {
                            val dm = dbManipulation.getinstance(context)
                            dm.Daoa().deletes(notes.id)
                        }
                        true
                    }
                    else -> true

                }

            }
            popupMenu.show()
        }


    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    inner class Viewholder(var binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Table) {
            binding.notezs = data
            binding.executePendingBindings()
        }

    }


}