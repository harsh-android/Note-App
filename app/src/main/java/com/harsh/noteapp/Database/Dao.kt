package com.harsh.noteapp.Database

import androidx.room.*
import androidx.room.Dao
import androidx.room.OnConflictStrategy.REPLACE
import com.harsh.noteapp.Model.Notes


@Dao
interface Dao {

    @Insert(onConflict = REPLACE)
    fun insertData(notes:Notes)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes() : List<Notes>

    @Query("UPDATE notes SET title= :title, note=:notes, color=:color, pinned=:pinned WHERE id=:id")
    fun updateData(id:Int, title:String, notes:String, color:String, pinned:Boolean)

    @Delete
    fun delete(notes:Notes)

}