package my.utils.mynoteskotlin.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import my.utils.mynoteskotlin.models.AppNote

@Dao
interface Dao {

    @Query("SELECT*FROM notes_table")
    fun getAllNotes(): LiveData<List<AppNote>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: AppNote)

    @Delete
    suspend fun delete(note: AppNote)
}