package com.example.noteapp.di

import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.NoteDao
import com.example.noteapp.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
// @Module
// Marks this class as a Hilt Module, which is used to tell Hilt how to construct and provide dependencies.
@Module
//Tells Hilt where this module will be used.
//SingletonComponent = Application-wide lifecycle (entire app lifetime).
//Think of it as: "This module provides dependencies that live as long as the app."
@InstallIn(SingletonComponent::class)
object AppModule {

    // @Provides
    // Provides a NoteDao instance to Hilt.
    /// Hilt will use this function to know how to build a NoteDao.
    @Provides
    /// So you're saying: "Wait... where does Hilt get that noteDatabase from?"
    /// Hilt gets it from another @Provides function in the same module.
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao()
    }
    @Singleton
    @Provides
    fun  provideAppDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}