package com.czerny.smarthomecare.chatroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.czerny.smarthomecare.MainActivity
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.R
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Message
import com.czerny.smarthomecare.databinding.FragmentChatroomBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.home.HomeViewModel
import com.czerny.smarthomecare.login.UserManager


class ChatRoomFragment: Fragment() {

    private val viewModel by viewModels<ChatRoomViewModel> { getVmFactory() }

    lateinit var binding : FragmentChatroomBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        /**-----chatroom---------*/
        val myUserEmail = UserManager.user.email
//        val friendUserEmail = viewModel.currentChattingUser


        binding = FragmentChatroomBinding.inflate(inflater, container, false)
        binding.viewModelChatroom = viewModel
        binding.lifecycleOwner = this

        val chatRoomAdapter = ChatRoomAdapter()
        binding.recyclerviewChatroom.adapter = chatRoomAdapter


        /**-----chatroom edit enter---------*/
/*        viewModel.enterMessage.observe(viewLifecycleOwner, Observer {
            chatRoomAdapter.submitList(it)
        })*/
        /**-----chatroom edit enter---------*/


        /**-----mock data---------*/
        val mockData: MutableList<Message> = mutableListOf()
        mockData.add(Message("","2","測試","","測試"))
        mockData.add(Message("","2","測試","","今天"))
        mockData.add(Message("","2","測試","","如何"))
        mockData.add(Message("","2","測試","","非常好"))
        mockData.add(Message("","2","測試","","是吧"))
        mockData.add(Message("","2","測試","","現在別亂跑"))
        mockData.add(Message("","2","測試","","懂"))
        viewModel.editableList = mockData
        chatRoomAdapter.submitList(viewModel.editableList)
        viewModel.Mockdata.observe(viewLifecycleOwner, Observer {
            it?.let {
                chatRoomAdapter.notifyDataSetChanged()
            }
        })
        /**-----mock data---------*/


        if (activity is MainActivity) {
            (activity as MainActivity).mainToolBar("聊天室")
        }


//        binding.layoutButtonSend.setOnClickListener {
//            if (isEmpty()) {
//                Toast.makeText(SmartHomeCareApplication.appContext, getString(R.string.reminder_chatroom_message), Toast.LENGTH_SHORT).show()
//            } else {
//                sendMessage(myUserEmail, friendUserEmail)
//            }
//        }



        return binding.root
    }


    fun isEmpty(): Boolean {
        return when (viewModel.enterMessage.value) {
            null -> true
            else -> false
        }
    }

    private fun sendMessage(myEmail: String, friendEmail: String) {
        viewModel.postMessage(viewModel.getUserEmails(myEmail, friendEmail), viewModel.getMessage())
        binding.editChatroomKeyin.text.clear()
    }

}