package com.czerny.smarthomecare.profile.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.viewModels
import com.czerny.smarthomecare.databinding.DialogProfileAddBinding



import com.czerny.smarthomecare.ext.getVmFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore

class ProfileAddDataDialog : BottomSheetDialogFragment() {

    private val viewModel by viewModels<ProfileAddDataViewModel> { getVmFactory() }
    val db = FirebaseFirestore.getInstance()

    private lateinit var binding: DialogProfileAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogProfileAddBinding.inflate(inflater, container, false)
        binding.viewModeProfileAddData = viewModel
        binding.lifecycleOwner = this


        binding.buttonProfileAddSave.setOnClickListener {
            val user: MutableMap<String, Any> = HashMap()

            user["profileNameData"] = viewModel.userProfile.profileNameData
            user["birth"] = viewModel.userProfile.birth
            user["year"] = viewModel.userProfile.year



            /**將資料上傳到firebase*/
            db.collection("profileDate") //firebase的名字是（）裡面的
                .add(user)
                .addOnSuccessListener { documentReference -> Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.id) }
                .addOnFailureListener { e -> Log.w("TAG", "Error adding document", e) }
            /**將資料上傳到firebase*/
        }

        return binding.root
    }
}