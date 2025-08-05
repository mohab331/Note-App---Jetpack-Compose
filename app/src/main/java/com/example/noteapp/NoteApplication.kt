package com.example.noteapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
/// This annotation initializes Hilt's dependency injection container.
/// It must be placed on your Application class.
/// Hilt uses it to generate the base component (SingletonComponent) which manages app-wide dependencies.
class NoteApplication : Application() {
}