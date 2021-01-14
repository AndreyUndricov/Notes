package my.utils.mynoteskotlin.screens.start

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_start.*
import my.utils.mynoteskotlin.R
import my.utils.mynoteskotlin.databinding.FragmentStartBinding
import my.utils.mynoteskotlin.util.*

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: StartFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)

        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        mBinding.btnRoom.setOnClickListener {
            mViewModel.initDatabase(TYPE_ROOM) {
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }
        mBinding.btnFirebase.setOnClickListener {
            mBinding.inputEmail.visibility = View.VISIBLE
            mBinding.inputPassword.visibility = View.VISIBLE
            mBinding.btnLogin.visibility = View.VISIBLE
            mBinding.btnLogin.setOnClickListener {

                val inputEmail = mBinding.inputEmail.text.toString()
                val inputPassword = mBinding.inputPassword.toString()
                if (inputEmail.isNotEmpty() && inputPassword.isNotEmpty()) {
                    EMAIL = inputEmail
                    PASSWORD = inputPassword
                    mViewModel.initDatabase(TYPE_FIREBASE) {
                        APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
                    }
                } else
                    showToast("Заполните поля регистрации")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}