package com.harsh.noteapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
class Notes : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "title")
    var title = ""

    @ColumnInfo(name = "note")
    var note = ""

    @ColumnInfo(name = "date")
    var date = ""

    @ColumnInfo(name = "color")
    var color = ""

    @ColumnInfo(name = "pinned")
    var pinned = false



    constructor(
        id: Int,
        title: String,
        note: String,
        date: String,
        color: String,
        pinned: Boolean
    ) {
        this.id = id
        this.title = title
        this.note = note
        this.date = date
        this.color = color
        this.pinned = pinned
    }

}