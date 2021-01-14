package my.utils.mynoteskotlin.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import my.utils.mynoteskotlin.R
import my.utils.mynoteskotlin.databinding.FragmentMainBinding
import my.utils.mynoteskotlin.models.AppNote
import my.utils.mynoteskotlin.util.APP_ACTIVITY

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: MainFragmentViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainAdapter
    private lateinit var mObserverList:Observer<List<AppNote>>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()

    }

    private fun initialization() {
        mAdapter = MainAdapter()
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter=mAdapter
        mObserverList = Observer<List<AppNote>> {
            val list = it.asReversed()
            mAdapter.setList(list)
        }
        mViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        mViewModel.allNotes.observe(this,mObserverList)
        mBinding.btnAddNote.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mViewModel.allNotes.removeObserver(mObserverList)
        mRecyclerView.adapter=null
    }

    companion object{
        fun click(note:AppNote){
            val bundle=Bundle()
            bundle.putSerializable("note",note)
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noteFragment,bundle)

        }
    }


}