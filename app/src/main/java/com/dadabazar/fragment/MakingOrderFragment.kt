package com.dadabazar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import com.dadabazar.MainActivity
import com.dadabazar.R

class MakingOrderFragment : Fragment() {
    private lateinit var btnBackCatalog: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_making_order, container, false)
        btnBackCatalog = view.findViewById(R.id.btnBackCatalog)

        btnBackCatalog.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@MakingOrderFragment)
                .commit()
            (activity as MainActivity).backBasketFragment()
        }
        return view
    }
}