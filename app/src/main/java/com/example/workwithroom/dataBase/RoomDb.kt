package com.example.workwithroom.dataBase
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class RoomDb : RoomDatabase() {

    abstract fun noteDao(): NoteDao?

    companion object {
        @Volatile
        private var INSTANCE: RoomDb? = null

        fun getDatabase(context: Context): RoomDb = Companion.INSTANCE?: synchronized(this) {
            INSTANCE?: (Room.databaseBuilder(
                context.applicationContext,
                RoomDb::class.java,
                "AppDB").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                    .build()
                    ).also { INSTANCE= it }
            }
    }
}


