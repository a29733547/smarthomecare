package com.czerny.smarthomecare.addremind.item

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.czerny.smarthomecare.MainActivity
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.databinding.DialogAddremindEditBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.home.HomeViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap


class AddRemindEditDialog : AppCompatDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.RemindDialog)
    }


    private val viewModel by viewModels<AddRemindEditViewModel> { getVmFactory() }

    private lateinit var binding: DialogAddremindEditBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogAddremindEditBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
//        binding.isLiveDataDesign = SmartHomeCareApplication.instance.isLiveDataDesign()
        binding.addremindEditViewModel = viewModel

        binding.buttonRemindSelectData.setOnClickListener{
            findNavController().navigate(AddRemindEditDialogDirections.actionAddRemindEditDialogToItemAddRemindData())
        }

        /**---------ItemAddRemindData 選擇日期後會這這邊Log會看到*/
        findNavController().currentBackStackEntry?.savedStateHandle?.
        getLiveData<String>("RemindData")?.observe(viewLifecycleOwner) { data ->
            binding.textAddremindData.text = data

        }





    // 2021/5/19 git checkout test

        return binding.root
    }


}