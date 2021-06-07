package com.czerny.smarthomecare.savedata.remind

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
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Remind
import com.czerny.smarthomecare.databinding.FragmentHomeBinding
import com.czerny.smarthomecare.databinding.FragmentSavedataRemindBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.home.HomeAdapter
import com.czerny.smarthomecare.home.HomeViewModel
import com.czerny.smarthomecare.savedata.SaveDataTypeFilter

class SaveDataRemindFragment(private val saveDataType:SaveDataTypeFilter) : Fragment(){

    private val viewModel by viewModels<SaveDataRemindViewModel> { getVmFactory() }

    lateinit var binding : FragmentSavedataRemindBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSavedataRemindBinding.inflate(inflater, container, false)
        binding.savedataRemindViewModel = viewModel
        binding.isLiveDataDesign = SmartHomeCareApplication.instance.isLiveDataDesign()
        binding.lifecycleOwner = this

        binding.recyclerviewSavedataRemind.layoutManager = LinearLayoutManager(context)
//        binding.recyclerviewSavedataRemind.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        val saveDataRemindAdapter = SaveDataRemindAdapter(viewModel, SaveDataRemindAdapter.OnClickListener{
            viewModel.navigateToRemindModify(it)
        })
        binding.recyclerviewSavedataRemind.adapter = saveDataRemindAdapter

        viewModel.navigateToRemindModify.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(SaveDataHealthFragmentDirections.
                actionSaveDataRemindFragmentToSaveDataRemindModifyFragment(it))
                viewModel.onRemindModifylNavigated() //no this can't go back
            }

        })


        return binding.root
    }

}

