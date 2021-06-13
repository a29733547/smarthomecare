package com.czerny.smarthomecare.savedata

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.czerny.smarthomecare.savedata.remind.SaveDataHealthFragment
import com.czerny.smarthomecare.savedata.remind.SaveDataRemindFragment

class SaveDataAdapteer(fragmentManager:FragmentManager, val family: String): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return SaveDataRemindFragment(SaveDataTypeFilter.values()[position], family)
            else -> return SaveDataHealthFragment(SaveDataTypeFilter.values()[position], family)
        }

//        return SaveDataRemindFragment(SaveDataTypeFilter.values()[position])
//        return SaveDataFragment(when (position){
//            0 -> SaveDataTypeFilter.REMIND
//            else -> SaveDataTypeFilter.HEALTH
//        })
    }

    override fun getCount() = SaveDataTypeFilter.values().size

    override fun getPageTitle(position: Int): CharSequence? {
        return SaveDataTypeFilter.values()[position].value
    }


}