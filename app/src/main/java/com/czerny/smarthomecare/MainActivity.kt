package com.czerny.smarthomecare

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.czerny.smarthomecare.chatroom.ChatRoomViewModel
import com.czerny.smarthomecare.databinding.ActivityMainBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView


//class MainActivity : AppCompatActivity() {
class MainActivity : BaseActivity() {

    private val viewModel by viewModels<MainViewModel> { getVmFactory() }

//    private val viewModel: MainViewModel by lazy {
//        ViewModelProvider(this).get(MainViewModel::class.java)
//    }

    lateinit var binding: ActivityMainBinding


    var remindTimeData: String = ""

    /**-----botton Navigation fun-------*/
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.homeFragment -> {
                findNavController(R.id.myNavHostFragment).navigate(NavGraphDirections.navigateToHomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.saveDataFragment -> {
                findNavController(R.id.myNavHostFragment).navigate(NavGraphDirections.navigateToSavedataFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.addRemindDialog -> {
                findNavController(R.id.myNavHostFragment).navigate(NavGraphDirections.navigateToAddremindDialog())
                return@OnNavigationItemSelectedListener true
            }
            R.id.profileFragment -> {
                findNavController(R.id.myNavHostFragment).navigate(NavGraphDirections.navigateToProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.chatRoomFragment -> {
                findNavController(R.id.myNavHostFragment).navigate(NavGraphDirections.navigateToChatroomFragment())
                return@OnNavigationItemSelectedListener true

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

    fun mainToolBar(newTitle: String) {
        binding.textToolbarTitle.visibility = View.VISIBLE
        binding.textToolbarTitle.text = newTitle
        binding.toolbar.visibility = View.VISIBLE
        binding.navView.visibility = View.VISIBLE
    }





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