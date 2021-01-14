package my.utils.mynoteskotlin.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import my.utils.mynoteskotlin.R
import my.utils.mynoteskotlin.databinding.FragmentNoteBinding
import my.utils.mynoteskotlin.models.AppNote
import my.utils.mynoteskotlin.util.APP_ACTIVITY

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: NoteFragmentViewModel
    private lateinit var mCurrentNote: AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)

        mCurrentNote = arguments?.getSerializable("note") as AppNote
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mViewModel=ViewModelProvider(this).get(NoteFragmentViewModel::class.java)
        mBinding.noteName.text = mCurrentNote.name
        mBinding.noteText.text = mCurrentNote.text
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId.equals(R.id.btn_delete)){
            mViewModel.delete(mCurrentNote){
                APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}