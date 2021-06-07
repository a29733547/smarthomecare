package com.czerny.smarthomecare.addremind.item

import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.databinding.DialogAddremindBinding
import com.czerny.smarthomecare.databinding.DialogAddremindEditBinding
import com.czerny.smarthomecare.databinding.DialogAddremindHealthBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.home.HomeViewModel
import com.czerny.smarthomecare.savedata.remind.SaveDataHealthViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Document
import java.util.*
import kotlin.collections.HashMap

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
