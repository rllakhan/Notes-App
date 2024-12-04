package com.example.notesapp

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao) {

    fun getAllNotes() : LiveData<ArrayList<Note>> {
        return notesDao.getAllNotes()
    }

    suspend fun insetNote(note: Note) {
        notesDao.insertNote(note)
    }
}