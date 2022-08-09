package com.moashrafff.developnetworktask.views.pages.b_SignUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moashrafff.developnetworktask.R
import com.moashrafff.developnetworktask.databinding.FragmentLandingBinding
import com.moashrafff.developnetworktask.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var _binding: FragmentRegisterBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}