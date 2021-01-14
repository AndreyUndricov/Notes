package my.utils.mynoteskotlin.database

import androidx.lifecycle.LiveData
import my.utils.mynoteskotlin.models.AppNote

interface DatabaseRepository {

    val allNotes: LiveData<List<AppNote>>
    suspend fun insert(note: AppNote, onSuccess: () -> Unit)
    suspend fun delete(note: AppNote, onSuccess: () -> Unit)

    fun connectFireBase(onSuccess: () -> Unit, onFail: (String) -> Unit) {}
    fun signOut() {}
}