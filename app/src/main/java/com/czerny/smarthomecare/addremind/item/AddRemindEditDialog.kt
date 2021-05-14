package com.czerny.smarthomecare.addremind.item

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.navigation.fragment.findNavController
import com.czerny.smarthomecare.MainActivity
import com.czerny.smarthomecare.databinding.DialogAddremindEditBinding


class AddRemindEditDialog : AppCompatDialogFragment() {

    private lateinit var binding: DialogAddremindEditBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogAddremindEditBinding.inflate(inflater, container, false)

        binding.buttonRemindSelectData.setOnClickListener{
            findNavController().navigate(AddRemindEditDialogDirections.actionAddRemindEditDialogToItemAddRemindData())
        }

        (activity as MainActivity).remindTimeData
        Log.i("wuwuhaha", "remindEdit =${(activity as MainActivity).remindTimeData}")



//// 獲取意圖物件
//        // 獲取意圖物件
//        val intent = getIntent()
////獲取傳遞的值
////獲取傳遞的值
//        val str = intent.getStringExtra("data")
////設定值
////設定值
//        binding.textAddremindData.setText(str)


        return binding.root
    }

}