package com.czerny.smarthomecare.savedata.remind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.FamilyInfo
import com.czerny.smarthomecare.databinding.FragmentSavedataRemindBinding
import com.czerny.smarthomecare.ext.getVmFactory

import com.czerny.smarthomecare.savedata.SaveDataTypeFilter

class SaveDataRemindFragment(private val saveDataType: SaveDataTypeFilter, private val family: String) : Fragment(){

    private val viewModel by viewModels<SaveDataRemindViewModel> { getVmFactory(
    ) }


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

        viewModel.getFamily = family

        viewModel.getLiveRemindResult(family)
        viewModel.getRemindResult(family)



//        Log.i("getfamily","getfamily = ${viewModel.getfamily}")
//        Log.i("getfamily","family = ${family}")

        return binding.root
    }

}

