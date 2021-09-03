package com.dadabazar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.dadabazar.MainActivity
import com.dadabazar.R

class ForgotYourPasswordFragment : Fragment() {
    private lateinit var btnBackCatalog: ImageView
    private lateinit var btnLoginHere: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forgot_your_password, container, false)
        btnBackCatalog = view.findViewById(R.id.btnBackCatalog)
        btnLoginHere = view.findViewById(R.id.btnLoginHere)

        btnLoginHere.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@ForgotYourPasswordFragment)
                .commit()
            (activity as MainActivity).backOfficeFragment()
        }

        btnBackCatalog.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@ForgotYourPasswordFragment)
                .commit()
            (activity as MainActivity).backOfficeFragment()
        }
        return view
    }
}