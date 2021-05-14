package com.czerny.smarthomecare.savedata.remind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.databinding.FragmentHomeBinding
import com.czerny.smarthomecare.databinding.FragmentSavedataRemindBinding
import com.czerny.smarthomecare.home.HomeAdapter
import com.czerny.smarthomecare.home.HomeViewModel
import com.czerny.smarthomecare.savedata.SaveDataTypeFilter

class SaveDataRemindFragment(private val saveDataType:SaveDataTypeFilter) : Fragment(){
    private val viewModel: SaveDataRemindViewModel by lazy {
        ViewModelProvider(this).get(SaveDataRemindViewModel::class.java)
    }

    lateinit var binding : FragmentSavedataRemindBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedataRemindBinding.inflate(inflater, container, false)
        binding.savedataRemindViewModel = viewModel
        binding.lifecycleOwner = this

        val homeAdapter = HomeAdapter()
        binding.recyclerviewSavedataRemind.adapter = homeAdapter

        val mockData: MutableList<MockData> = mutableListOf()
        mockData.add(MockData(1, "teest", "提醒：每天吃藥", "123", "123", "am:09:00", "每天"))
        mockData.add(MockData(1, "teest", "提醒：每天吃藥", "123", "123", "am:09:00", "每天"))
        mockData.add(MockData(1, "teest", "提醒：每天吃藥", "123", "123", "am:09:00", "每天"))
        mockData.add(MockData(1, "teest", "提醒：每天吃藥", "123", "123", "am:09:00", "每天"))

        viewModel.editableList = mockData
        homeAdapter.submitList(viewModel.editableList)
        viewModel.Mockdata.observe(viewLifecycleOwner, Observer {
            it?.let {
                homeAdapter.notifyDataSetChanged()
            }
        })




        return binding.root
    }

}

