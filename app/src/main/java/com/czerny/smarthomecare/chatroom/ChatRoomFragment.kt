package com.czerny.smarthomecare.chatroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.data.Message
import com.czerny.smarthomecare.databinding.FragmentChatroomBinding



class ChatRoomFragment: Fragment() {

    private val viewModel: ChatRoomViewModel by lazy {
        ViewModelProvider(this).get(ChatRoomViewModel::class.java)
    }

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
        mockData.add(Message("1","2","3","4","測試"))
        mockData.add(Message("1","2","3","4","今天"))
        mockData.add(Message("1","2","3","4","天氣"))
        mockData.add(Message("1","2","3","4","如何"))
        mockData.add(Message("1","2","3","4","爺爺奶奶爸爸媽嘰嘰歪歪"))
        viewModel.editableList = mockData
        chatRoomAdapter.submitList(viewModel.editableList)
        viewModel.Mockdata.observe(viewLifecycleOwner, Observer {
            it?.let {
                chatRoomAdapter.notifyDataSetChanged()
            }
        })


        return binding.root
    }

}