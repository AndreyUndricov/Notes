package my.utils.mynoteskotlin.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import my.utils.mynoteskotlin.models.AppNote

class AllNotesLiveData:LiveData<List<AppNote>>() {
    private val mAuth = FirebaseAuth.getInstance()
    private val mDatabaseReference=FirebaseDatabase.getInstance().reference
        .child(mAuth.currentUser?.uid.toString())
    private  val listener= object :ValueEventListener{
        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            TODO("Not yet implemented")
        }

    }
}