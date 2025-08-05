package com.example.noteapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes_tbl")

data class NoteModel(
    @PrimaryKey
    var id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "note_title")
    var title: String = "",

    @ColumnInfo(name = "note_content")
    var content: String = "",

    @ColumnInfo(name = "creation_date")
    var creationDate: Date = Date.from(Instant.now())
) {
    override fun toString(): String {
        return "NoteModel(id=$id, title='$title', content='$content', timestamp=$creationDate)"
    }
}
