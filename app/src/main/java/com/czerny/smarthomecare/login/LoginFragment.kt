//package com.czerny.smarthomecare.login
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import com.czerny.smarthomecare.databinding.FragmentLoginBinding
//import com.czerny.smarthomecare.ext.getVmFactory
//
//class LoginFragment : Fragment() {
//
//    private val viewModel by viewModels<LoginViewModel> { getVmFactory() }
//
//    lateinit var binding : FragmentLoginBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        binding = FragmentLoginBinding.inflate(inflater, container, false)
//
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = this
//
//
//
//        return binding.root
//    }
//
//}