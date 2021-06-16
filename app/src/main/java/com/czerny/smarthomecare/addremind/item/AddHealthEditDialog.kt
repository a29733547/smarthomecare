package com.czerny.smarthomecare.addremind.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.databinding.DialogAddremindHealthBinding
import com.czerny.smarthomecare.ext.getVmFactory
import java.util.*

class AddHealthEditDialog : AppCompatDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.RemindDialog)
    }

    private val viewModel by viewModels<AddHealthEditViewModel> { getVmFactory(
        AddHealthEditDialogArgs.fromBundle(requireArguments()).familyName
    ) }


    private lateinit var binding: DialogAddremindHealthBinding

    //  20210527 branch test


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DialogAddremindHealthBinding.inflate(inflater, container, false)
        binding.viewModelAddHealth = viewModel
        binding.isLiveDataDesign = SmartHomeCareApplication.instance.isLiveDataDesign()
        binding.lifecycleOwner = viewLifecycleOwner

        binding.buttonHealthEdit.setOnClickListener {
            viewModel.addHealthDataFun(viewModel.familyNema)
        }

        binding.buttonHealthSelectData.setOnClickListener {
            findNavController().navigate(AddHealthEditDialogDirections.actionAddHealthEditDialogToItemAddRemindData())
        }

        /**---------ItemAddRemindData 選擇日期後會這這邊Log會看到*/
        findNavController().currentBackStackEntry?.savedStateHandle?.
        getLiveData<String>("RemindData")?.observe(viewLifecycleOwner) { data ->
            binding.textAddHealthData.text = data

        }

        return binding.root
    }
}
