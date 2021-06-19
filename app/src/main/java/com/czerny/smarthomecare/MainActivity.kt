package com.czerny.smarthomecare

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.czerny.smarthomecare.databinding.ActivityMainBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.savedata.SaveDataTypeFilter
import com.czerny.smarthomecare.savedata.remind.SaveDataRemindFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.system.measureNanoTime


//20210619 branch test

class MainActivity : AppCompatActivity() {

    val CHANNEL_ID = "channelId"
    val CHANNEL_NAME = "channelName"
    val NOTIFICATION_ID = 0

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



        createNotificationChannel()

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Title")
            .setContentText("Content")
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)

        binding.buttonNotify.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }


    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
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