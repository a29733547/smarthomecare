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
        mockData.add(MockData(1, "teest", "吃藥", "123", "123", "09:00", "2021/05/14"))
        mockData.add(MockData(1, "teest", "回診", "123", "123", "13:00", "2021/05/15"))
        mockData.add(MockData(1, "teest", "抽血", "123", "123", "10:30", "2021/05/16"))
        mockData.add(MockData(1, "teest", "住院", "123", "123", "18:00", "2021/05/17"))

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

