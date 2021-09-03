package com.dadabazar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.dadabazar.MainActivity
import com.dadabazar.R

class AddOrderFragment : Fragment() {
    private lateinit var btnBack: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_order, container, false)
        btnBack = view.findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            (activity as MainActivity).loadFragment(ControlPanelFragment())
        }
        return view
    }
}