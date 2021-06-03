package com.czerny.smarthomecare.chatroom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.czerny.smarthomecare.MainActivity
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.ChatRoom

import com.czerny.smarthomecare.databinding.FragmentChatroomBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.home.HomeViewModel
import com.czerny.smarthomecare.login.UserManager
import com.czerny.smarthomecare.util.Logger


class ChatRoomFragment : Fragment() {

    private val viewModel by viewModels<ChatRoomViewModel> {
        getVmFactory(

        )
    }

    lateinit var binding: FragmentChatroomBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentChatroomBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val chatRoomAdapter = ChatRoomAdapter()
        binding.recyclerviewChatroom.adapter = chatRoomAdapter

        binding.recyclerviewChatroom.layoutManager = LinearLayoutManager(context)



        viewModel.allLiveMessage.observe(viewLifecycleOwner, Observer {
            chatRoomAdapter.submitList(it)
        })


//test

        if (activity is MainActivity) {
            (activity as MainActivity).mainToolBar("聊天室")
        }

        binding.layoutButtonSend.setOnClickListener {

            viewModel.sendMessage(UserManager.user.userId)
            viewModel.enterMessage.value = ""
        }

        return binding.root
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().navigateUp()
            return true
        }
        return false
    }

}