package com.example.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteapp.models.NoteModel
import com.example.noteapp.utils.Converters

//entities = [NoteModel::class]
// List of @Entity classes (tables) for the DB
//This tells Room which data classes represent tables in your database.


//exportSchema = false
//Tells Room whether or not to export the schema (your DB structure) to a file.

//abstract fun noteDao(): NoteDao
//You must declare abstract functions in your RoomDatabase to give Room access to your DAOs.
//Room auto-generates the implementation under the hood.

@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteDatabase :RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "note_database"
    }
}