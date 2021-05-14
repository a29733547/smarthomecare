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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.czerny.smarthomecare.MainActivity
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.databinding.DialogAddremindBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddRemindDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogAddremindBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.RemindDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {




        //        binding.layoutRemind.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_up))
        binding = DialogAddremindBinding.inflate(inflater, container, false)


        binding.buttonAddremindRemind.setOnClickListener{




            findNavController().navigate(AddRemindDialogDirections.actionAddRemindDialogToAddRemindEditDialog())
        }
        binding.buttonAddremindHealth.setOnClickListener{
//            Log.d("Hiya", "date=${(activity as MainActivity).data}")
            findNavController().navigate(AddRemindDialogDirections.actionAddRemindDialogToAddhealthEditDialog())
        }

        return binding.root
    }

//    override fun dismiss() {
//        binding.layoutRemind.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_down))
//        Handler().postDelayed({ super.dismiss() }, 200)
//    }

}