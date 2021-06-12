package com.czerny.smarthomecare.savedata.modify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.databinding.FragmentSavedataRemindModifyBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.home.HomeFragmentArgs


class SaveDataRemindModifyFragment : Fragment() {

//    lateinit var binding : FragmentSavedataRemindModifyBinding

    private val viewModel by viewModels<SaveDataRemindModifyViewModel> { getVmFactory(
        SaveDataRemindModifyFragmentArgs.fromBundle(requireArguments()).remindKey,
        SaveDataRemindModifyFragmentArgs.fromBundle(requireArguments()).familyName
    )}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSavedataRemindModifyBinding.inflate(inflater, container, false)

        binding.viewModelRemindModify = viewModel
        binding.isLiveDataDesign = SmartHomeCareApplication.instance.isLiveDataDesign()
        binding.lifecycleOwner = viewLifecycleOwner

        binding.buttonRemindMofidySave.setOnClickListener {
            viewModel.healthRemindFun(viewModel.getFamily)
        }


        return binding.root
    }

}