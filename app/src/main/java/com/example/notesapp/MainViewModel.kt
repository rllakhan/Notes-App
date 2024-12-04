package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(private val notesRepository: NotesRepository): ViewModel() {
    fun getAllNotes(): LiveData<ArrayList<Note>> {
        return notesRepository.getAllNotes()
    }

    fun insertNote(note: Note) {
        runBlocking {
            launch {
                notesRepository.insetNote(note)
            }
        }
    }
}