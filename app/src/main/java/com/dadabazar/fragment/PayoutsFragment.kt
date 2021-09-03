package com.dadabazar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.jjoe64.graphview.GraphView



class PayoutsFragment : Fragment() {
    private lateinit var btnBack: ImageView
    private lateinit var graph: GraphView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_payouts, container, false)
        graph = view.findViewById(R.id.graph)


        btnBack = view.findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            (activity as MainActivity).loadFragment(ControlPanelFragment())
        }
        return view
    }
}