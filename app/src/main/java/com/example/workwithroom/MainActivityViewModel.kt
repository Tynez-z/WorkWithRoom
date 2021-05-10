package com.example.workwithroom
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.workwithroom.dataBase.NoteEntity
import com.example.workwithroom.dataBase.RoomDb

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {
    lateinit var allNotes: MutableLiveData<List<NoteEntity>>

    init {
        allNotes = MutableLiveData()
    }

    fun getAllNotesObservers(): MutableLiveData<List<NoteEntity>> {
        return allNotes
    }

    fun getAllNotes() {
        val noteDao = RoomDb.getDatabase((getApplication()))?.noteDao()
        val list = noteDao?.getAllNotesInfo()
        allNotes.postValue(list)
    }

    fun insertNoteInfo(entity: NoteEntity) {
        val noteDao = RoomDb.getDatabase(getApplication())?.noteDao()
        noteDao?.insertNote(entity)
        getAllNotes()
    }

    fun updateNoteInfo(entity: NoteEntity) {
        val noteDao = RoomDb.getDatabase(getApplication())?.noteDao()
        noteDao?.updateNote(entity)
        getAllNotes()
    }

    fun deleteeNoteInfo(entity: NoteEntity) {
        val noteDao = RoomDb.getDatabase(getApplication())?.noteDao()
        noteDao?.deleteNote(entity)
        getAllNotes()
    }

}