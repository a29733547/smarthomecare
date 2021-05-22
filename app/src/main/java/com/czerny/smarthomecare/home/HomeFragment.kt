package com.czerny.smarthomecare.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.czerny.smarthomecare.*
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.databinding.FragmentHomeBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.savedata.SaveDataTypeFilter
import com.czerny.smarthomecare.savedata.remind.SaveDataHealthViewModel

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel> { getVmFactory() }

    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeViewModel = viewModel
        binding.isLiveDataDesign = SmartHomeCareApplication.instance.isLiveDataDesign()
        binding.lifecycleOwner = this

        binding.recyclerViewHome.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewHome.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        val homeAdapter = HomeAdapter()
        binding.recyclerViewHome.adapter = homeAdapter


//        val mockData: MutableList<Remind> = mutableListOf()
//        mockData.add(Remind(1, "teest", "吃藥", "123", "123", "09:00", "2021/05/14"))
//        mockData.add(Remind(1, "teest", "回診", "123", "123", "13:00", "2021/05/15"))
//        mockData.add(Remind(1, "teest", "抽血", "123", "123", "10:30", "2021/05/16"))
//        mockData.add(Remind(1, "teest", "住院", "123", "123", "18:00", "2021/05/17"))
//
//        viewModel.editableList = mockData
//        homeAdapter.submitList(viewModel.editableList)
//        viewModel.Mockdata.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                homeAdapter.notifyDataSetChanged()
//            }
//        })

        if (activity is MainActivity) {
            (activity as MainActivity).mainToolBar("提醒")
        }


        return binding.root
    }


}