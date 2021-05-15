package com.czerny.smarthomecare.addremind.item


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView.OnDateChangeListener
import androidx.navigation.fragment.findNavController
import com.czerny.smarthomecare.MainActivity
import com.czerny.smarthomecare.databinding.ItemRemindDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*


class ItemAddRemindData : BottomSheetDialogFragment() {

    private lateinit var binding: ItemRemindDataBinding



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = ItemRemindDataBinding.inflate(inflater, container, false)




        /**-------選取日期資料-------*/
        binding.calendarViewReminddata.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->
            val sdf = SimpleDateFormat("yyyy/MM/dd")
            val calendar = Calendar.getInstance()
            calendar[year, month] = dayOfMonth
            val sDate = sdf.format(calendar.time)
            Log.d("Czerny", "sDate formatted: $sDate")

            val testabd = "what is this"
//            (activity as MainActivity).remindTimeData = sDate





            binding.buttonRemindGetdata.setOnClickListener {
                Log.d("czerny", "date=${(activity as MainActivity).remindTimeData}")
                findNavController().previousBackStackEntry?.savedStateHandle?.set("RemindData", sDate)
                findNavController().popBackStack()
            }

//            var activity: MainActivity
//            var map: Map<String, String>
//            var heightData: String
//            var weightData: String
//
//            activity = (getActivity() as MainActivity?)!!
//            map = activity.map
//
//            heightData = map["KEY_HEIGHT"]!!
//            weightData = map["KEY_WEIGHT"]!!

            //只能在activity
//            //建立意圖物件
//            //建立意圖物件
//            val intent= Intent(this, AddRemindEditDialog::class.java)
////設定傳遞鍵值對
////設定傳遞鍵值對
//            intent.putExtra("data", sDate)
////啟用意圖
////啟用意圖
//            startActivity(intent)

        })

//        //監聽器（收）
//        parentFragmentManager.setFragmentResultListener("key", this, object : FragmentResultListener() {
//            fun onFragmentResult(key: String, bundle: Bundle) {
//                // 這裡使用的是String，但是任何其他能夠被放在Bundle中的資料型別都是支援的
//                val result = bundle.getString("bundleKey")
//                //做一些其他事情
//            }
//        })
//
//        //傳
//        button.setOnClickListener(View.OnClickListener {
//            val result = Bundle()
//            result.putString("bundleKey", "result")
//            parentFragmentManager.setFragmentResult("requestKey", result)
//        })

        //activity ??
//        SharedPreferences.edit().putString("Token", “123456”).commit();// 保存
//        SharedPreferences.getString("Token", "")// 获取



        return binding.root
    }



}