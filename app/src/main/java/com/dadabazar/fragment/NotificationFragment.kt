package com.dadabazar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import com.dadabazar.MainActivity
import com.dadabazar.R

class NotificationFragment : Fragment() {

    private lateinit var clickMainFragment: AppCompatButton
    private lateinit var btnBackHome: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notification, container, false)
        clickMainFragment = view.findViewById(R.id.clickMainFragment)
        btnBackHome = view.findViewById(R.id.btnBackHome)

        clickMainFragment.setOnClickListener {
            (activity as MainActivity).backFragmentMain()
        }
        btnBackHome.setOnClickListener {
            (activity as MainActivity).backFragmentMain()
        }
        return view
    }
}