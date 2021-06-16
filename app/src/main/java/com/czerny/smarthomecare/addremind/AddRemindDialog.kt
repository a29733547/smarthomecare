package com.czerny.smarthomecare.addremind

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.czerny.smarthomecare.MainActivity
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.databinding.DialogAddremindBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.home.HomeFragmentArgs
import com.czerny.smarthomecare.home.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddRemindDialog : BottomSheetDialogFragment() {

    private val viewModel by viewModels<AddRemindViewModel> { getVmFactory(
            AddRemindDialogArgs.fromBundle(requireArguments()).familyName
    ) }

    private lateinit var binding: DialogAddremindBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.RemindDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        binding = DialogAddremindBinding.inflate(inflater, container, false)

        binding.buttonAddremindRemind.setOnClickListener{
            findNavController().navigate(AddRemindDialogDirections.actionAddRemindDialogToAddRemindEditDialog(viewModel.familyName))
        }

        binding.buttonAddremindHealth.setOnClickListener{
            findNavController().navigate(AddRemindDialogDirections.actionAddRemindDialogToAddhealthEditDialog(viewModel.familyName))
        }





        return binding.root
    }

}