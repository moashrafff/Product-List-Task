package com.moashrafff.developnetworktask.views.pages.b_SignUp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.moashrafff.developnetworktask.R
import com.moashrafff.developnetworktask.data.model.User
import com.moashrafff.developnetworktask.data.source.UserPreferences
import com.moashrafff.developnetworktask.databinding.FragmentLandingBinding
import com.moashrafff.developnetworktask.databinding.FragmentRegisterBinding
import com.moashrafff.developnetworktask.views.pages.a_Login.LoginFragmentDirections
import com.moashrafff.developnetworktask.views.pages.a_Login.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var _binding: FragmentRegisterBinding
    private val binding get() = _binding

    @Inject
    lateinit  var userSharedEmail: UserPreferences
    private val userViewModel: UserViewModel by  viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(layoutInflater)
        (activity as AppCompatActivity).supportActionBar?.title = "Register"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.registerBtn.setOnClickListener {
            when {
                binding.regPhoneEt.text.toString().trim().length<11 -> {
                    Toast.makeText(requireContext(), "phone should consist of minimum 11 number", Toast.LENGTH_SHORT).show()
                }
                binding.regEmailEt.text.toString().trim().isEmpty() -> {
                    Toast.makeText(requireContext(), "check your data", Toast.LENGTH_SHORT).show()
                }
                !Patterns.EMAIL_ADDRESS.matcher(binding.regEmailEt.text.toString().trim()).matches() -> {
                    Toast.makeText(requireContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                }
                binding.regPassEt.text.toString().trim().length<8 -> {
                    Toast.makeText(requireContext(), "Password should consist of minimum 8 characters", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    userViewModel.register(
                        User(null,
                            binding.regPhoneEt.text.toString().trim(),
                            binding.regPassEt.text.toString().trim(),
                      binding.regEmailEt.text.toString().trim()+binding.regPhoneEt.text.toString().trim(),
                            binding.regEmailEt.text.toString().trim(),
                            "true"
                        )
                    )

                }
            }

        }


        binding.loginTv.setOnClickListener{
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }



    }
}