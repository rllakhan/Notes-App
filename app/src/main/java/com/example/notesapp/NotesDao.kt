package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {
    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM notes")
    fun getAllNotes() : LiveData<ArrayList<Note>>
}