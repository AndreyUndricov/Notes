package my.utils.mynoteskotlin.database.room

import androidx.lifecycle.LiveData
import my.utils.mynoteskotlin.database.DatabaseRepository
import my.utils.mynoteskotlin.models.AppNote

class AppRoomRepository(private val appRoomDao:Dao):DatabaseRepository {
    override val allNotes: LiveData<List<AppNote>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.insert(note)
        onSuccess()
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.delete(note)
        onSuccess()
    }
}