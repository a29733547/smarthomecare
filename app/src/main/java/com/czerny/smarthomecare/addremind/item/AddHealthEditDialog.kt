package com.czerny.smarthomecare.addremind.item

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.ViewModelProvider
import com.czerny.smarthomecare.databinding.DialogAddremindBinding
import com.czerny.smarthomecare.databinding.DialogAddremindEditBinding
import com.czerny.smarthomecare.databinding.DialogAddremindHealthBinding
import com.czerny.smarthomecare.home.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap

class AddHealthEditDialog : AppCompatDialogFragment() {

    private val viewModel: AddHealthEditViewModel by lazy {
        ViewModelProvider(this).get(AddHealthEditViewModel::class.java)
    }



    private lateinit var binding: DialogAddremindHealthBinding
    val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogAddremindHealthBinding.inflate(inflater, container, false)
        binding.viewModelAddHealth = viewModel
        binding.lifecycleOwner = this


        binding.buttonRemindEdit.setOnClickListener{

            val user: MutableMap<String, Any> = HashMap()

            user["title"] = viewModel.health.title
            user["name"] = viewModel.health.name
            user["place"] = viewModel.health.place
            user["content"] = viewModel.health.content
            user["note"] = viewModel.health.note


            /**將資料上傳到firebase*/
            db.collection("healthDate") //firebase的名字是（）裡面的
                .add(user)
                .addOnSuccessListener { documentReference -> Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.id) }
                .addOnFailureListener { e -> Log.w("TAG", "Error adding document", e) }
            /**將資料上傳到firebase*/

        }

        //test brancn

        return binding.root
    }
}