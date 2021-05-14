package com.czerny.smarthomecare.addremind.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.czerny.smarthomecare.databinding.DialogAddremindBinding
import com.czerny.smarthomecare.databinding.DialogAddremindEditBinding
import com.czerny.smarthomecare.databinding.DialogAddremindHealthBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddHealthEditDialog : AppCompatDialogFragment() {

    private lateinit var binding: DialogAddremindHealthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogAddremindHealthBinding.inflate(inflater, container, false)


        return binding.root
    }
}