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