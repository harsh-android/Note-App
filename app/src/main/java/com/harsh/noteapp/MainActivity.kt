package com.harsh.noteapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.harsh.noteapp.Activitys.AddNoteActivity
import com.harsh.noteapp.Adapter.NotesAdapter
import com.harsh.noteapp.Database.RoomDB
import com.harsh.noteapp.Model.Notes
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var notes: List<Notes>
    companion object {
        lateinit var adapter: NotesAdapter
        lateinit var roomDB: RoomDB
        fun UpdateLoadNotes() {
            var final_notes: ArrayList<Notes> = ArrayList()
            var note = roomDB.mainDao().getAllNotes()
            final_notes.clear()
            for (x in 0..note.size - 1) {
                if (note.get(x).pinned)
                    final_notes.add(note.get(x))
            }
            for (x in 0..note.size - 1) {
                if (!note.get(x).pinned)
                    final_notes.add(note.get(x))
            }

            adapter.update(final_notes)

        }
    }


    override fun onResume() {
        super.onResume()

        LoadNotes()
    }


    fun LoadNotes() {
        var final_notes: ArrayList<Notes> = ArrayList()
        var note = roomDB.mainDao().getAllNotes()
        final_notes.clear()

        for (x in 0..note.size - 1) {
            if (note.get(x).pinned)
                final_notes.add(note.get(x))
        }
        for (x in 0..note.size - 1) {
            if (!note.get(x).pinned)
                final_notes.add(note.get(x))
        }

        adapter = NotesAdapter(this, final_notes)
        note_list.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roomDB = RoomDB.getInstance(this)


        note_list.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        LoadNotes()


        add_note.setOnClickListener {

            startActivity(Intent(this, AddNoteActivity::class.java))

        }

    }
}