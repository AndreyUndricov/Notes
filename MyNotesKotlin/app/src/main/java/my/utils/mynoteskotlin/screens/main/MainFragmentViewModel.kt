package my.utils.mynoteskotlin.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import my.utils.mynoteskotlin.util.REPOSITORY

class MainFragmentViewModel(application: Application):AndroidViewModel(application) {
    val allNotes= REPOSITORY.allNotes

}