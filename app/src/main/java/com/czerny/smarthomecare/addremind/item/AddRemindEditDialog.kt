package com.czerny.smarthomecare.addremind.item

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.czerny.smarthomecare.MainActivity
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.databinding.DialogAddremindEditBinding
import com.czerny.smarthomecare.home.HomeViewModel


class AddRemindEditDialog : AppCompatDialogFragment() {



    private val viewModel: AddHealthEditViewModel by lazy {
        ViewModelProvider(this).get(AddHealthEditViewModel::class.java)
    }

    private lateinit var binding: DialogAddremindEditBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogAddremindEditBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.addremindEditViewModel = viewModel

        binding.buttonRemindSelectData.setOnClickListener{
            findNavController().navigate(AddRemindEditDialogDirections.actionAddRemindEditDialogToItemAddRemindData())
        }

        /**---------ItemAddRemindData 選擇日期後會這這邊Log會看到*/
        findNavController().currentBackStackEntry?.savedStateHandle?.
        getLiveData<String>("RemindData")?.observe(viewLifecycleOwner) { data ->
            viewModel.getRemindTimeData = data


            Log.i("howdoyoudo","gogoback = ${data}")
            Log.i("howdoyoudo", "gogo= ${viewModel.getRemindTimeData}")
        }


//        (activity as MainActivity).remindTimeData
//        Log.i("wuwuhaha", "remindEdit =${(activity as MainActivity).remindTimeData}")




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

    /**---------ItemAddRemindData 選擇日期後會這這邊Log會看到*/
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        findNavController().currentBackStackEntry?.savedStateHandle?.
//        getLiveData<String>("RemindData")?.observe(viewLifecycleOwner) { data ->
//            viewModel.getRemindTimeData = data
//            Log.i("howdoyoudo","gogoback = ${data}")
//            Log.i("howdoyoudo", "gogo= ${viewModel.getRemindTimeData}")
//        }
//    }
}