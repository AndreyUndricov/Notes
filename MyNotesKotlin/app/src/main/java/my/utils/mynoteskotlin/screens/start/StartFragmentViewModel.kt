package my.utils.mynoteskotlin.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import my.utils.mynoteskotlin.database.firebase.AppFirebaseRepository
import my.utils.mynoteskotlin.database.room.AppRoomDatabase
import my.utils.mynoteskotlin.database.room.AppRoomRepository
import my.utils.mynoteskotlin.util.REPOSITORY
import my.utils.mynoteskotlin.util.TYPE_FIREBASE
import my.utils.mynoteskotlin.util.TYPE_ROOM
import my.utils.mynoteskotlin.util.showToast

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE ->{
               REPOSITORY=AppFirebaseRepository()
                REPOSITORY.connectFireBase({onSuccess()},{ showToast("Nou")})
            }
        }
    }
}