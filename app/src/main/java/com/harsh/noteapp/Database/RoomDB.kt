package com.harsh.noteapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.harsh.noteapp.Model.Notes
import java.math.MathContext

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    companion object {
        var Database_name = "NoteApp"

        fun getInstance(context: Context): RoomDB {
            var roomDB = Room.databaseBuilder(
                context.applicationContext,
                RoomDB::class.java,
                Database_name
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            return roomDB
        }
    }

    abstract fun mainDao(): Dao

}