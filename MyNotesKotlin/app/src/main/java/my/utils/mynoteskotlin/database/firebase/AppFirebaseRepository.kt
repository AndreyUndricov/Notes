package my.utils.mynoteskotlin.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import my.utils.mynoteskotlin.database.DatabaseRepository
import my.utils.mynoteskotlin.models.AppNote
import my.utils.mynoteskotlin.util.EMAIL
import my.utils.mynoteskotlin.util.PASSWORD

class AppFirebaseRepository:DatabaseRepository {

    private val mAuth=FirebaseAuth.getInstance()

    override val allNotes: LiveData<List<AppNote>>
        get() = TODO("Not yet implemented")

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun connectFireBase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener {onFail(it.message.toString()) }
            }
    }

    override fun signOut() {
        mAuth.signOut()
    }
}