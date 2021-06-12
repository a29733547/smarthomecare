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
import com.czerny.smarthomecare.savedata.modify.SaveDataRemindModifyFragmentArgs
import java.util.*

class AddHealthEditDialog : AppCompatDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.RemindDialog)
    }

    private val viewModel by viewModels<AddHealthEditViewModel> { getVmFactory() }


    private lateinit var binding: DialogAddremindHealthBinding

    //  20210527 branch test


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DialogAddremindHealthBinding.inflate(inflater, container, false)
        binding.viewModelAddHealth = viewModel
        binding.isLiveDataDesign = SmartHomeCareApplication.instance.isLiveDataDesign()
        binding.lifecycleOwner = viewLifecycleOwner

        binding.buttonHealthSelectData.setOnClickListener {
            findNavController().navigate(AddHealthEditDialogDirections.actionAddHealthEditDialogToItemAddRemindData())
        }

        /**---------ItemAddRemindData 選擇日期後會這這邊Log會看到*/
        findNavController().currentBackStackEntry?.savedStateHandle?.
        getLiveData<String>("RemindData")?.observe(viewLifecycleOwner) { data ->
            binding.textAddHealthData.text = data

        }

//        binding.buttonHealthEdit.setOnClickListener{

//
//            val user: MutableMap<String, Any> = HashMap()
//            user["id"] = db.collection("healthDate").document().id
//            user["createdTime"] = Calendar.getInstance().timeInMillis
////            user["id"] = viewModel.health.id
//            user["title"] = viewModel.health.title
//            user["name"] = viewModel.health.name
//            user["healthPlaceData"] = viewModel.health.healthPlaceData
//            user["content"] = viewModel.health.content
//            user["note"] = viewModel.health.note
//
//
//
//            db.collection("healthDate")
//                .add(user)
//                .addOnSuccessListener { documentReference -> Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.id) }
//                .addOnFailureListener { e -> Log.w("TAG", "Error adding document", e) }
//
//
//        }





        //test brancn

        return binding.root
    }
}
