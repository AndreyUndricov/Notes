package my.utils.mynoteskotlin.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.utils.mynoteskotlin.models.AppNote
import my.utils.mynoteskotlin.util.REPOSITORY

class NoteFragmentViewModel(application: Application):AndroidViewModel(application) {
fun delete(note:AppNote,onSuccess:()->Unit){
    viewModelScope.launch(Dispatchers.IO) {
        REPOSITORY.delete(note){
            onSuccess()
        }

    }
}
}