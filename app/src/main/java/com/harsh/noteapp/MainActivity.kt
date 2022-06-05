package com.harsh.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.harsh.noteapp.Activitys.AddNoteActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        add_note.setOnClickListener{

            startActivity(Intent(this,AddNoteActivity::class.java))

        }

    }
}