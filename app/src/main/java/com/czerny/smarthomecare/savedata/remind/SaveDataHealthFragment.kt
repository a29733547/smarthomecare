package com.czerny.smarthomecare.savedata.remind

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.databinding.FragmentSavedataHealthBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.home.HomeAdapter
import com.czerny.smarthomecare.home.HomeViewModel
import com.czerny.smarthomecare.savedata.SaveDataTypeFilter
import com.czerny.smarthomecare.util.Logger
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume

class SaveDataHealthFragment(private val saveDataType: SaveDataTypeFilter) : Fragment() {


    private val viewModel by viewModels<SaveDataHealthViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSavedataHealthBinding.inflate(inflater, container, false)
        binding.viewModelSaveDataHealth = viewModel
        binding.isLiveDataDesign = SmartHomeCareApplication.instance.isLiveDataDesign()
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerviewSavedataHealth.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewSavedataHealth.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        val saveDataHealthAdapter = SaveDataHealthAdapter()
        binding.recyclerviewSavedataHealth.adapter = saveDataHealthAdapter




        return binding.root
    }

}