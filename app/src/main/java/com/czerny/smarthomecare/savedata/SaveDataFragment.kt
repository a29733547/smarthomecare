package com.czerny.smarthomecare.savedata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.czerny.smarthomecare.databinding.FragmentSavedataBinding
import com.google.android.material.tabs.TabLayout

class SaveDataFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            FragmentSavedataBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
                viewpagerSavedata.let {
                    tabsSavedata.setupWithViewPager(it)
                    it.adapter = SaveDataAdapteer(childFragmentManager)
                    it.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabsSavedata))
                }
                return@onCreateView root
            }

    }
    
}