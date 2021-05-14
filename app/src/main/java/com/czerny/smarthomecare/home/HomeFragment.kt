package com.czerny.smarthomecare.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.databinding.FragmentHomeBinding

class HomeFragment:Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeViewModel = viewModel
        binding.lifecycleOwner = this

        val homeAdapter = HomeAdapter()
        binding.recyclerViewHome.adapter = homeAdapter

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