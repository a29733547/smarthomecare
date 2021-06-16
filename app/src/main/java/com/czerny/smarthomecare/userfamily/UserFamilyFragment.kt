package com.czerny.smarthomecare.userfamily


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.czerny.smarthomecare.MainActivity
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

        val familyAdapter = FamilyAdapter(FamilyAdapter.OnClickListener{})
        binding.recyclerFamily.adapter = familyAdapter

        binding.imageFamilyAdd.setOnClickListener {
            viewModel.addFamily()
        }

        viewModel.liveFamilyInfo.observe(viewLifecycleOwner, Observer {
            it?.let {
                familyAdapter.submitList(it)
            }
        })

        if (activity is MainActivity) {
            (activity as MainActivity).userFamilyBar()
        }



        return binding.root
    }

}