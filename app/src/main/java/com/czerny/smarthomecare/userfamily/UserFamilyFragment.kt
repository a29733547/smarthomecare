package com.czerny.smarthomecare.userfamily


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

import androidx.navigation.fragment.findNavController
import com.czerny.smarthomecare.data.FamilyInfo
import com.czerny.smarthomecare.databinding.FragmentUserFamilyListBinding
import com.czerny.smarthomecare.ext.getVmFactory


class UserFamilyFragment: Fragment() {

    private val viewModel by viewModels<UserFamilyViewModel> { getVmFactory() }

    lateinit var binding: FragmentUserFamilyListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserFamilyListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


//        val userAdapter = UserAdapter(UserAdapter.OnClickListener{})
//        binding.recyclerUser.adapter = userAdapter


        val familyAdapter = FamilyAdapter(FamilyAdapter.OnClickListener{})
        binding.recyclerFamily.adapter = familyAdapter

        binding.buttonUserFamilyCheck.setOnClickListener {
            findNavController().navigate(UserFamilyFragmentDirections.actionUserFamilyFragmentToHomeFragment())
        }

        viewModel.liveFamilyInfo.observe(viewLifecycleOwner, Observer {
            it?.let {
                familyAdapter.submitList(it)
            }
        })





        return binding.root
    }

}