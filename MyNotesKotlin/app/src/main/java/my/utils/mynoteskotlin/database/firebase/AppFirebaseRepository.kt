package my.utils.mynoteskotlin.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import my.utils.mynoteskotlin.database.DatabaseRepository
import my.utils.mynoteskotlin.models.AppNote
import my.utils.mynoteskotlin.util.*

class AppFirebaseRepository:DatabaseRepository {

    init {
        AUTH= FirebaseAuth.getInstance()
    }

    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
       val idNote = REF_DATABASE.push().key.toString()
        val mapNote= hashMapOf<String,Any>()
        mapNote[ID_FIREBASE]=idNote
        mapNote[NAME]=note.name
        mapNote[TEXT]=note.text

        REF_DATABASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
       REF_DATABASE.child(note.idFirebase).removeValue()
           .addOnSuccessListener { onSuccess() }
           .addOnFailureListener { showToast(it.message.toString()) }
    }

    override fun connectFireBase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener {onFail(it.message.toString()) }
            }

        CURRENT_ID= AUTH.currentUser?.uid.toString()
        REF_DATABASE=FirebaseDatabase.getInstance().reference
            .child(CURRENT_ID)
    }

    override fun signOut() {
        AUTH.signOut()
    }
}