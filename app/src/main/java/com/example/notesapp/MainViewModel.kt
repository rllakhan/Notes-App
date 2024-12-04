package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(private val notesRepository: NotesRepository): ViewModel() {
    fun getAllNotes(): LiveData<List<Note>> {
        return notesRepository.getAllNotes()
    }

    fun insertNote(note: Note) {
        runBlocking {
            launch {
                notesRepository.insetNote(note)
            }
        }
    }

    fun updateNote(note: Note) {
        runBlocking {
            launch {
                notesRepository.updateNote(note)
            }
        }
    }

    fun deleteNote(note: Note) {
        runBlocking {
            launch {
                notesRepository.deleteNote(note)
            }
        }
    }
}