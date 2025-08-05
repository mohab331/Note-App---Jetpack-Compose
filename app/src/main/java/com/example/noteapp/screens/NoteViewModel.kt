package com.example.noteapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.models.NoteModel
import com.example.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/// @Inject constructor
/// Tells Hilt: “Inject the required dependencies into this class.”
/// Hilt looks for:
//A NoteDao provider (which it finds in AppModule)
//And injects it into NoteViewModel

@HiltViewModel
/// Use @HiltViewModel:
//When your ViewModel needs dependencies injected via constructor (like DAO, Repository)
class NoteViewModel @Inject constructor(private  val  noteRepository: NoteRepository) : ViewModel() {
    /// MutableStateFlow -  A mutable, observable data holder that can emit new values. Like ValueNotifier in Flutter.
    private val _noteListStateFlow = MutableStateFlow<List<NoteModel>>(emptyList())

    /// StateFlow -  A read-only Flow that emits the latest value and allows UI to collect from it.
    /// Exposing only StateFlow ensures that UI can read but not modify the state — enforcing encapsulation.
    /// Always use it to expose internal mutable state from ViewModel.
    val noteList: StateFlow<List<NoteModel>> = _noteListStateFlow.asStateFlow() //  is an extension function in Kotlin used to expose a read-only version of a MutableStateFlow


//    MutableStateFlow
//    You can change its value using .value = ...
//    Used inside ViewModel
//    Mutable variables are useful for changing app state (like counters, form fields, etc.).

//    StateFlow
//    Read-only version
//    UI can only observe, not modify
//    for safety, we expose immutable types to the UI to prevent accidental updates (clean architecture principle).

    init {
        viewModelScope.launch {
            noteRepository.getAllNotes().collect { noteListFromDB ->
                _noteListStateFlow.value = noteListFromDB
            }
        }
    }

    fun  addNote(noteModel: NoteModel) = viewModelScope.launch {
        noteRepository.addNote(noteModel)
    }

    fun updateNote(noteModel: NoteModel) = viewModelScope.launch {
        noteRepository.updateNote(noteModel)
    }

    fun deleteNote(noteModel: NoteModel) = viewModelScope.launch {
        noteRepository.deleteNote(noteModel)
    }

    fun deleteAllNotes() = viewModelScope.launch {
        noteRepository.deleteAllNotes()
    }

    fun getAllNotes() = viewModelScope.launch {
        noteRepository.getAllNotes().collect { notesListFromDB ->
            _noteListStateFlow.value= notesListFromDB
        }
    }
}