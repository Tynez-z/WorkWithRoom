package com.example.workwithroom.dataBase
import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM noteinfo ORDER BY id DESC")
    fun getAllNotesInfo(): List<NoteEntity>?

    @Insert
    fun insertNote(note: NoteEntity?)

    @Delete
    fun deleteNote(note: NoteEntity?)

    @Update
    fun updateNote(note: NoteEntity?)
}