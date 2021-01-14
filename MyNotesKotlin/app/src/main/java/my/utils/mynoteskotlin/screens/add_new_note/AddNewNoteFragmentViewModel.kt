package my.utils.mynoteskotlin.screens.add_new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.utils.mynoteskotlin.models.AppNote
import my.utils.mynoteskotlin.util.REPOSITORY

class AddNewNoteFragmentViewModel(application: Application):AndroidViewModel(application) {
  fun insert (note:AppNote,onSuccess:()->Unit){
      viewModelScope.launch(Dispatchers.IO){
          REPOSITORY.insert(note){
              onSuccess()

          }
      }
  }
}