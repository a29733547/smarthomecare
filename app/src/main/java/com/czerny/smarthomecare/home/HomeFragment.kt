package com.czerny.smarthomecare.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.czerny.smarthomecare.*
import com.czerny.smarthomecare.databinding.FragmentHomeBinding
import com.czerny.smarthomecare.ext.getVmFactory


class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel> { getVmFactory(
        HomeFragmentArgs.fromBundle(requireArguments()).familyName
    ) }

    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeViewModel = viewModel
        binding.isLiveDataDesign = SmartHomeCareApplication.instance.isLiveDataDesign()
        binding.lifecycleOwner = this

        binding.recyclerViewHome.layoutManager = LinearLayoutManager(context)

        val homeAdapter = HomeAdapter(viewModel, HomeAdapter.OnClickListener{

        })
        binding.recyclerViewHome.adapter = homeAdapter



        if (activity is MainActivity) {
            (activity as MainActivity).mainToolBar("提醒事項")
        }

        if (activity is MainActivity) {
            (activity as MainActivity).getFamilyName = viewModel.familyName
        }



        return binding.root
    }




}