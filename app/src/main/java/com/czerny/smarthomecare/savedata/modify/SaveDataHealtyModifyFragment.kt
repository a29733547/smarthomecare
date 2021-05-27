package com.czerny.smarthomecare.savedata.modify

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.databinding.FragmentSavedataHealthMidifyBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.profile.ProfileViewModel
import com.czerny.smarthomecare.util.Logger
import com.google.firebase.firestore.FirebaseFirestore

class SaveDataHealtyModifyFragment : Fragment() {

    private val viewModel by viewModels<SaveDataHealthModifyViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSavedataHealthMidifyBinding.inflate(inflater, container, false)
        binding.viewModelHealthModify = viewModel
        binding.isLiveDataDesign = SmartHomeCareApplication.instance.isLiveDataDesign()
        binding.lifecycleOwner = viewLifecycleOwner





        return binding.root
    }
}