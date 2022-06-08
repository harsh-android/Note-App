package com.harsh.noteapp.Activitys

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.harsh.noteapp.Database.RoomDB
import com.harsh.noteapp.Model.Notes
import com.harsh.noteapp.R
import kotlinx.android.synthetic.main.activity_add_note.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    lateinit var selectedColor:String
    private val TAG = "AddNoteActivity"
    lateinit var roomDB: RoomDB

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        roomDB = RoomDB.getInstance(this)

        select_color.setOnClickListener {
            MaterialColorPickerDialog
                .Builder(this)
                .setTitle("Pick Theme")
                .setColorShape(ColorShape.SQAURE)
                .setDefaultColor(Color.RED)
                .setColorListener { color, colorHex ->
                    selected_color.setCardBackgroundColor(Color.parseColor(colorHex))
                    selectedColor = colorHex
                }
                .show()
        }

        save_note.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())

            var listing = roomDB.mainDao().getAllNotes()

            var id = 0;
            try {
                if (listing.size <= 0) {
                    id = 1;
                } else {
                    id = listing.get(0).id
                    id++
                }
                Log.e(TAG, "onCreate: Last ID "+listing.get(listing.size-1).id  )
                Log.e(TAG, "onCreate: Array Size "+listing.size)
            }catch (e:Exception) {
                id = 1
            }

            var notes = Notes(id,ed_title.text.toString(), ed_notes.contentText, currentDate,selectedColor,false)


            roomDB.mainDao().insertData(notes)
            finish()

        }


    }
}