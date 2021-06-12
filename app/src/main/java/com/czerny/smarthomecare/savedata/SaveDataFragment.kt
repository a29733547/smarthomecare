package com.czerny.smarthomecare.savedata

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.czerny.smarthomecare.MainActivity

import com.czerny.smarthomecare.databinding.FragmentSavedataBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.home.HomeFragmentArgs
import com.google.android.material.tabs.TabLayout

class SaveDataFragment : Fragment() {

    private val viewModel by viewModels<SaveDataViewModel> { getVmFactory(
        SaveDataFragmentArgs.fromBundle(requireArguments()).familyName
    )}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentSavedataBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewpagerSavedata.let {
                tabsSavedata.setupWithViewPager(it)


                it.adapter = SaveDataAdapteer(childFragmentManager, viewModel.familyName)
                Log.i("adapter","it = ${viewModel.familyName}")
                it.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabsSavedata))
            }

            if (activity is MainActivity) {
                (activity as MainActivity).mainToolBar("記錄")
            }

            return@onCreateView root
        }


    }


}