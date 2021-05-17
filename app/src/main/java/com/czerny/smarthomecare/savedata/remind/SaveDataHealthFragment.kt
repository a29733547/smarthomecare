package com.czerny.smarthomecare.savedata.remind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.databinding.FragmentSavedataHealthBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.home.HomeAdapter
import com.czerny.smarthomecare.home.HomeViewModel
import com.czerny.smarthomecare.savedata.SaveDataTypeFilter

class SaveDataHealthFragment(private val saveDataType: SaveDataTypeFilter) : Fragment() {

    //    private val viewModel: SaveDataHealthViewModel by lazy {
//        ViewModelProvider(this).get(SaveDataHealthViewModel::class.java)
//    }
    private val viewModel by viewModels<SaveDataHealthViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSavedataHealthBinding.inflate(inflater, container, false)
        binding.viewModelSaveDataHealth = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val saveDataHealthAdapter = SaveDataHealthAdapter()
        binding.recyclerviewSavedataHealth.adapter = saveDataHealthAdapter


        val mockData: MutableList<Health> = mutableListOf()
        mockData.add(Health("1", "台大醫院", "台大醫院", "爺爺", "123", "09:00"))
        mockData.add(Health("1", "住家", "住家", "爺爺", "123", "13:00"))
        mockData.add(Health("1", "耕莘醫院", "耕莘醫院", "爺爺", "123", "10:30"))
        mockData.add(Health("1", "亞東醫院", "亞東醫院", "爺爺", "123", "18:00"))

        viewModel.editableList = mockData
        saveDataHealthAdapter.submitList(viewModel.editableList)
        viewModel.health.observe(viewLifecycleOwner, Observer {
            it?.let {
                saveDataHealthAdapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

}