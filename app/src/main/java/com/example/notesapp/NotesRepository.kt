package com.example.notesapp

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao) {

    fun getAllNotes() : LiveData<List<Note>> {
        return notesDao.getAllNotes()
    }

    suspend fun insetNote(note: Note) {
        notesDao.insertNote(note)
    }

    suspend fun updateNote(note: Note) {
        notesDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note)
    }
}