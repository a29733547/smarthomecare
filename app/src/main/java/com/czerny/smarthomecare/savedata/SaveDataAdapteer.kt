package com.czerny.smarthomecare.savedata

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.czerny.smarthomecare.savedata.remind.SaveDataRemindFragment

class SaveDataAdapteer(fragmentManager:FragmentManager): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(position: Int): Fragment {
        return SaveDataRemindFragment(SaveDataTypeFilter.values()[position])
    }

    override fun getCount() = SaveDataTypeFilter.values().size

    override fun getPageTitle(position: Int): CharSequence? {
        return SaveDataTypeFilter.values()[position].value
    }
}