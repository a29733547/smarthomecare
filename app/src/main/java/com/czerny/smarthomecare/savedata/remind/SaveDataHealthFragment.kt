package com.czerny.smarthomecare.savedata.remind

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.czerny.smarthomecare.MockData
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Health
import com.czerny.smarthomecare.databinding.FragmentSavedataHealthBinding
import com.czerny.smarthomecare.ext.getVmFactory
import com.czerny.smarthomecare.home.HomeAdapter
import com.czerny.smarthomecare.home.HomeViewModel
import com.czerny.smarthomecare.savedata.SaveDataTypeFilter
import com.czerny.smarthomecare.util.Logger
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume

class SaveDataHealthFragment(private val saveDataType: SaveDataTypeFilter) : Fragment() {

//        private val viewModel: SaveDataHealthViewModel by lazy {
//        ViewModelProvider(this).get(SaveDataHealthViewModel::class.java)
//    }
    private val viewModel by viewModels<SaveDataHealthViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSavedataHealthBinding.inflate(inflater, container, false)
        binding.viewModelSaveDataHealth = viewModel
        binding.isLiveDataDesign = SmartHomeCareApplication.instance.isLiveDataDesign()
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerviewSavedataHealth.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewSavedataHealth.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        val saveDataHealthAdapter = SaveDataHealthAdapter()
        binding.recyclerviewSavedataHealth.adapter = saveDataHealthAdapter


//        val mockData: MutableList<Health> = mutableListOf()
//        mockData.add(Health("1", "台大醫院", "台大醫院", "爺爺", "定檢", "09:00"))
//        mockData.add(Health("1", "住家", "住家", "爺爺", "血壓穩定", "13:00"))
//        mockData.add(Health("1", "耕莘醫院", "耕莘醫院", "爺爺", "急診", "10:30"))
//        mockData.add(Health("1", "亞東醫院", "亞東醫院", "爺爺", "心臟病", "18:00"))
//
//        viewModel.editableList = mockData
//        saveDataHealthAdapter.submitList(viewModel.editableList)
//        viewModel.health.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                saveDataHealthAdapter.notifyDataSetChanged()
//
//            }
//        })


//        val db = FirebaseFirestore.getInstance()
//
//        db.collection("healthDate")
//            .get()
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    val list = mutableListOf<Health>()
//                    for (document in task.result!!) {
//                        Log.d("getTheTAG", document.id + " => " + document.data)
//                        val getHealthData = document.toObject(Health::class.java)
//                        list.add(getHealthData)
//
//                    }
//                } else {
//                    Log.w("getTheTAG", "Error getting documents.", task.exception)
//                }
//            }
        /**Read*/
/*        val user: MutableMap<String, Any> = HashMap()
        user["title"] = viewModel.gethealth.title
        user["name"] = viewModel.gethealth.name
        user["place"] = viewModel.gethealth.palce
        user["content"] = viewModel.gethealth.content
        user["note"] = viewModel.gethealth.note*/

        /**Read*/




//        viewModel.liveHealth.observe(viewLifecycleOwner, Observer {
//            Logger.d("viewModel.liveArticles.observe, it=$it")
//            it?.let {
//                binding.viewModelSaveDataHealth = viewModel
//            }
//        })

        return binding.root
    }

}