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


class SaveDataRemindModifyFragment : Fragment() {

    private val viewModel by viewModels<SaveDataRemindModifyViewModel> {
        getVmFactory(SaveDataRemindModifyFragmentArgs.fromBundle(requireArguments()).remindKey) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
           val binding = FragmentSavedataRemindModifyBinding.inflate(inflater, container, false)

        binding.viewModelRemindModify = viewModel
        binding.isLiveDataDesign = SmartHomeCareApplication.instance.isLiveDataDesign()
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}