package com.example.noteapp.repository

import com.example.noteapp.data.NoteDao
import com.example.noteapp.models.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    suspend fun addNote(note: NoteModel) = noteDao.insertOrUpdateNote(note)
    suspend fun updateNote(note: NoteModel) = noteDao.updateNote(note)
    suspend fun deleteNote(note: NoteModel) = noteDao.deleteNote(note)
    suspend fun deleteAllNotes() = noteDao.deleteAllNotes()
    /// flowOn - changes the coroutine dispatcher (thread) where the upstream of a Flow is executed
    /// Use flowOn when you want to emit items on a background thread,
    /// Dispatchers.IO - A coroutine dispatcher optimized for blocking IO operations
    /// Because blocking on the Main thread causes ANRs. Dispatchers.IO provides a large thread pool for background work.
    fun getAllNotes() = noteDao.getAllNotes().flowOn(Dispatchers.IO) // Move this work to IO thread
}