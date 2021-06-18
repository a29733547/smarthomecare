package com.czerny.smarthomecare

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.czerny.smarthomecare.databinding.ActivityMainBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.savedata.SaveDataTypeFilter
import com.czerny.smarthomecare.savedata.remind.SaveDataRemindFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView


//20210619 branch test

class MainActivity : AppCompatActivity() {
    
    private val viewModel by viewModels<MainViewModel> { getVmFactory() }

    private lateinit var binding: ActivityMainBinding

    var getFamilyName : String = ""

    /**-----botton Navigation fun-------*/
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.homeFragment -> {
                findNavController(R.id.myNavHostFragment).navigate(NavGraphDirections.navigateToHomeFragment(getFamilyName))
                return@OnNavigationItemSelectedListener true
            }
            R.id.saveDataFragment -> {
                findNavController(R.id.myNavHostFragment).navigate(NavGraphDirections.navigateToSavedataFragment(getFamilyName))
                return@OnNavigationItemSelectedListener true
            }
            R.id.addRemindDialog -> {
                findNavController(R.id.myNavHostFragment).navigate(NavGraphDirections.navigateToAddremindDialog(getFamilyName))
                return@OnNavigationItemSelectedListener true
            }
            R.id.profileFragment -> {
                findNavController(R.id.myNavHostFragment).navigate(NavGraphDirections.navigateToProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.chatRoomFragment -> {
                findNavController(R.id.myNavHostFragment).navigate(NavGraphDirections.navigateToChatroomFragment(getFamilyName))

                return@OnNavigationItemSelectedListener true
                Log.i("getFamilyName", "${getFamilyName}")

            }
        }
        false
    }
    /**-----botton Navigation fun-------*/



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this


        setupBottomNav()



    }

    /**-----ToolBar visibility Fun-------*/
    fun userFamilyBar() {
        binding.toolbar.visibility = View.GONE
        binding.navView.visibility = View.GONE
    }

    fun mainToolBar(newTitle: String) {
        binding.textToolbarTitle.visibility = View.VISIBLE
        binding.textToolbarTitle.text = newTitle
        binding.toolbar.visibility = View.VISIBLE
        binding.navView.visibility = View.VISIBLE
    }
    /**-----ToolBar visibility Fun-------*/

    /**-----botton Navigation fun-------*/
    private fun setupBottomNav() {
        binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        val menuView = binding.navView.getChildAt(0) as BottomNavigationMenuView
        val itemView = menuView.getChildAt(2) as BottomNavigationItemView
//        val bindingBadge = BadgeBottomBinding.inflate(LayoutInflater.from(this), itemView, true)
//        bindingBadge.lifecycleOwner = this
//        bindingBadge.viewModel = viewModel
    }
    /**-----botton Navigation fun-------*/
}