package com.czerny.smarthomecare.savedata.remind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.databinding.FragmentSavedataHealthBinding
import com.czerny.smarthomecare.home.HomeAdapter
import com.czerny.smarthomecare.savedata.SaveDataTypeFilter

class SaveDataHealthFragment (private val saveDataType: SaveDataTypeFilter) : Fragment(){

    private val viewModel: SaveDataHealthViewModel by lazy {
        ViewModelProvider(this).get(SaveDataHealthViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
         val binding = FragmentSavedataHealthBinding.inflate(inflater, container, false)
        binding.viewModelSaveDataHealth = viewModel
        binding.lifecycleOwner = this

        val saveDataHealthAdapter = SaveDataHealthAdapter()
        binding.recyclerviewSavedataHealth.adapter = saveDataHealthAdapter

        val mockData: MutableList<MockData> = mutableListOf()
        mockData.add(MockData(1, "teest", "台大醫院", "爺爺", "123", "09:00", "穩定"))
        mockData.add(MockData(1, "teest", "住家", "爺爺", "123", "13:00", "高燒"))
        mockData.add(MockData(1, "teest", "耕莘醫院", "爺爺", "123", "10:30", "貧血中"))
        mockData.add(MockData(1, "teest", "亞東醫院", "爺爺", "123", "18:00", "心律不整"))

        viewModel.editableList = mockData
        saveDataHealthAdapter.submitList(viewModel.editableList)
        viewModel.Mockdata.observe(viewLifecycleOwner, Observer {
            it?.let {
                saveDataHealthAdapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

}