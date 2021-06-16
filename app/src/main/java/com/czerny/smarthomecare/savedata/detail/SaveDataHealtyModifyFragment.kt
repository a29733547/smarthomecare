package com.czerny.smarthomecare.savedata.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.databinding.FragmentSavedataHealthMidifyBinding
import com.czerny.smarthomecare.ext.getVmFactory

class SaveDataHealtyModifyFragment : Fragment() {

    private val viewModel by viewModels<SaveDataHealthModifyViewModel> { getVmFactory(
        SaveDataHealtyModifyFragmentArgs.fromBundle(requireArguments()).healthKey,
        SaveDataHealtyModifyFragmentArgs.fromBundle(requireArguments()).familyName
    )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSavedataHealthMidifyBinding.inflate(inflater, container, false)
        binding.viewModelHealthModify = viewModel
        binding.isLiveDataDesign = SmartHomeCareApplication.instance.isLiveDataDesign()
        binding.lifecycleOwner = viewLifecycleOwner

        binding.buttonSavedataHealthModifyPush.setOnClickListener {
            viewModel.healthModifyFun(viewModel.getFamily)
        }




        return binding.root
    }
}