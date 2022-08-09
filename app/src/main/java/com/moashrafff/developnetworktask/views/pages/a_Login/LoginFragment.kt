package com.moashrafff.developnetworktask.views.pages.a_Login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.moashrafff.developnetworktask.R
import com.moashrafff.developnetworktask.data.source.UserPreferences
import com.moashrafff.developnetworktask.databinding.FragmentLoginBinding
import com.moashrafff.developnetworktask.databinding.FragmentRegisterBinding
import com.moashrafff.developnetworktask.views.pages.b_SignUp.RegisterFragment
import com.moashrafff.developnetworktask.views.pages.b_SignUp.RegisterFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginBinding
    private val binding get() = _binding

    @Inject
    lateinit  var userSharedEmail: UserPreferences
    private val userViewModel: UserViewModel by  viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(layoutInflater)
        (activity as AppCompatActivity).supportActionBar?.title = "Login"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        userViewModel.getStatus(userSharedEmail.getUserMail()).observe(requireActivity(), Observer {
            if ( it== "true") {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToLandingFragment())

            }
        })

        binding.signUup.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.loginBtn.setOnClickListener {
            when {

                binding.loginPhoneEt.text.toString().trim().length<11 -> {
                    Toast.makeText(requireContext(), "phone is less than 11 numbers", Toast.LENGTH_SHORT).show()
                }
                binding.loginPassEt.text.toString().trim().length<8 -> {
                    Toast.makeText(requireContext(), "passowrd is less than 8 charcters", Toast.LENGTH_SHORT).show()
                }

                else -> {

                    runBlocking {

                        if (userViewModel.login(binding.loginPhoneEt.text.toString().trim())==binding.loginPassEt.text.toString().trim()){
                            userViewModel.updateStatus(userSharedEmail.getUserMail(),"true")
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToLandingFragment())

                        }else{
                            Toast.makeText(requireContext(), "log in failed", Toast.LENGTH_SHORT).show()
                        }
                    }
    }

}
        }
    }
}