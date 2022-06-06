package com.harsh.noteapp.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.harsh.noteapp.Database.RoomDB
import com.harsh.noteapp.MainActivity
import com.harsh.noteapp.Model.Notes
import com.harsh.noteapp.R
import kotlinx.android.synthetic.main.note_item.view.*
import java.util.ArrayList

class NotesAdapter(mainActivity: Activity, notes: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    var activity = mainActivity
    var dataList = notes
    var roomDB: RoomDB = RoomDB.getInstance(mainActivity)

    class NotesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_note = itemView.txt_notes
        var txt_title = itemView.txt_title
        var card_back = itemView.card_back
        var pinn = itemView.pinn

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        var view = LayoutInflater.from(activity).inflate(R.layout.note_item, parent, false)
        return NotesHolder(view)
    }

    override fun onBindViewHolder(holder: NotesHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.txt_title.text = dataList.get(position).title
        holder.txt_note.text = dataList.get(position).note

        holder.card_back.setCardBackgroundColor(Color.parseColor(dataList.get(position).color))

        holder.itemView.setOnLongClickListener(object :View.OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {

                var menu = PopupMenu(activity,p0)
                menu.menuInflater.inflate(R.menu.list_option,menu.menu)

                menu.setOnMenuItemClickListener(object :PopupMenu.OnMenuItemClickListener{
                    override fun onMenuItemClick(p0: MenuItem?): Boolean {

                        when(p0?.itemId) {

                            R.id.delete -> {
                                roomDB.mainDao().delete(dataList.get(position))
                                MainActivity.UpdateLoadNotes()
                            }




                        }

                        return false
                    }
                })

                menu.show()

                return false
            }
        })

        holder.pinn.setOnClickListener {

            var f_pin = false
            if (dataList.get(position).pinned)
                f_pin = false
                else
                    f_pin = true

            roomDB.mainDao().updateData(
                dataList.get(position).id,
                dataList.get(position).title,
                dataList.get(position).note,
                dataList.get(position).color,
                f_pin
            )
            MainActivity.UpdateLoadNotes()

        }

        if (dataList.get(position).pinned) {
            holder.pinn.setImageResource(R.drawable.fill_pin)
        } else {
            holder.pinn.setImageResource(R.drawable.blank_pin)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun update(finalNotes: ArrayList<Notes>) {

        dataList = finalNotes
        notifyDataSetChanged()

    }

}