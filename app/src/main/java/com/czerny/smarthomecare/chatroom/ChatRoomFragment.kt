package com.czerny.smarthomecare.chatroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.czerny.smarthomecare.MainActivity
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.data.Message
import com.czerny.smarthomecare.databinding.FragmentChatroomBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.home.HomeViewModel


class ChatRoomFragment: Fragment() {

    private val viewModel by viewModels<ChatRoomViewModel> { getVmFactory() }

//    private val viewModel: ChatRoomViewModel by lazy {
//        ViewModelProvider(this).get(ChatRoomViewModel::class.java)
//    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentChatroomBinding.inflate(inflater, container, false)
        binding.viewModelChatroom = viewModel
        binding.lifecycleOwner = this

        val chatRoomAdapter = ChatRoomAdapter()
        binding.recyclerviewChatroom.adapter = chatRoomAdapter

        val mockData: MutableList<Message> = mutableListOf()
        mockData.add(Message("","2","測試","","1"))
        mockData.add(Message("","2","測試","","2"))
        mockData.add(Message("","2","測試","","3"))
        mockData.add(Message("","2","測試","","4"))
        mockData.add(Message("","2","測試","","5"))
        mockData.add(Message("","2","測試","","6"))
        mockData.add(Message("","2","測試","","7"))

        viewModel.editableList = mockData
        chatRoomAdapter.submitList(viewModel.editableList)
        viewModel.Mockdata.observe(viewLifecycleOwner, Observer {
            it?.let {
                chatRoomAdapter.notifyDataSetChanged()
            }
        })

        if (activity is MainActivity) {
            (activity as MainActivity).mainToolBar("聊天室")
        }

        return binding.root
    }

}