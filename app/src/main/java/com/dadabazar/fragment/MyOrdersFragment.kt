package com.dadabazar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.utills.Preferences

class MyOrdersFragment : Fragment() {
    private lateinit var btnBack: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_orders, container, false)
        btnBack = view.findViewById(R.id.btnBack)

        if (Preferences.loadGroupId(requireContext()).toString().toInt() == 0 ||
            Preferences.loadGroupId(requireContext()).toString().toInt() == 1 ||
            Preferences.loadGroupId(requireContext()).toString().toInt() == 2
        ) {
            btnBack.setOnClickListener {
                (activity as MainActivity).loadFragment(ControlPanelFragment())
            }
        } else {
            btnBack.setOnClickListener {
                (activity as MainActivity).loadFragment(ProfileUserFragment())
            }
        }
        return view
    }
}