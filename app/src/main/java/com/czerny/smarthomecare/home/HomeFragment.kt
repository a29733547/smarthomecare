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
//        binding.recyclerViewHome.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        val homeAdapter = HomeAdapter(HomeAdapter.OnClickListener{})
        binding.recyclerViewHome.adapter = homeAdapter



        if (activity is MainActivity) {
            (activity as MainActivity).mainToolBar("提醒事項")
        }


        return binding.root
    }


}