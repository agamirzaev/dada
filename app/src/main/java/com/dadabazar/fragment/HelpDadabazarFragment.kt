package com.dadabazar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.utills.Preferences

class HelpDadabazarFragment : Fragment() {
    private lateinit var btnBack: ImageView
    private lateinit var clickPlus: ImageView
    private lateinit var clickMinus: ImageView
    private lateinit var relativeLayoutOauth: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help_dadabazar, container, false)
        btnBack = view.findViewById(R.id.btnBack)
        clickPlus = view.findViewById(R.id.clickPlus)
        clickMinus = view.findViewById(R.id.clickMinus)
        relativeLayoutOauth = view.findViewById(R.id.relativeLayoutOauth)

        clickMinus.setOnClickListener {
            clickMinus.visibility = View.GONE
            clickPlus.visibility = View.VISIBLE
            relativeLayoutOauth.visibility = View.GONE
        }

        clickPlus.setOnClickListener {
            clickMinus.visibility = View.VISIBLE
            clickPlus.visibility = View.GONE
            relativeLayoutOauth.visibility = View.VISIBLE
        }

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