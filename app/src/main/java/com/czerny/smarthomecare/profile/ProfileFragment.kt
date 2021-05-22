package com.czerny.smarthomecare.profile


import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.czerny.smarthomecare.MainActivity
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.databinding.FragmentProfileBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class ProfileFragment : Fragment(){




    private val viewModel by viewModels<ProfileViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.viewModelProfile = viewModel
        binding.lifecycleOwner = viewLifecycleOwner





        if (activity is MainActivity) {
            (activity as MainActivity).mainToolBar("個人資料")
        }

        binding.imageProfileAdd.setOnClickListener{
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToProfileAddDataDialog())
        }




        return binding.root
    }

}