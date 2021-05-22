package com.czerny.smarthomecare.savedata.remind

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.czerny.smarthomecare.SmartHomeCareApplication

import com.czerny.smarthomecare.databinding.FragmentSavedataHealthBinding
import com.czerny.smarthomecare.ext.getVmFactory

import com.czerny.smarthomecare.savedata.SaveDataTypeFilter


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

        val saveDataHealthAdapter = SaveDataHealthAdapter(SaveDataHealthAdapter.OnClickListener {
            viewModel.navigateToHealthModify(it)
        })
        binding.recyclerviewSavedataHealth.adapter = saveDataHealthAdapter

        viewModel.navigateToHealthModify.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(SaveDataHealthFragmentDirections.
                actionSaveDataHealthFragmentToSaveDataHealtyModifyFragment())
                viewModel.onHealthModifylNavigated() //no this can't go back
            }

        })



        return binding.root
    }

}