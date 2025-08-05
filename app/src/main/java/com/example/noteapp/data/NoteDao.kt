package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.models.NoteModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes_tbl ORDER BY creation_date DESC")
    fun getAllNotes(): Flow<List<NoteModel>>

    @Query("SELECT * FROM notes_tbl WHERE id = :noteId")
    suspend fun getNoteById(noteId: UUID): NoteModel?

    @Delete
    suspend fun deleteNote(note: NoteModel): Int

    @Query("DELETE FROM notes_tbl")
    suspend fun deleteAllNotes() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateNote(note: NoteModel) : Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: NoteModel) : Int
}