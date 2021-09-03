package com.dadabazar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.dadabazar.MainActivity
import com.dadabazar.R


class ControlPanelFragment : Fragment() {
    private lateinit var btnBack: ImageView

    private lateinit var linearLayoutMyOrders: LinearLayout
    private lateinit var linearLayoutClient: LinearLayout
    private lateinit var LinearLayoutWallet: LinearLayout
    private lateinit var LinearLayoutPayouts: LinearLayout
    private lateinit var LinearLayoutHelp: LinearLayout
    private lateinit var linearLayoutAddOrder: LinearLayout
    private lateinit var linearLayoutAccountSettings: LinearLayout
    private lateinit var LinearLayoutProductManagement:LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_control_panel, container, false)
        btnBack = view.findViewById(R.id.btnBack)
        linearLayoutMyOrders = view.findViewById(R.id.linearLayoutMyOrders)
        linearLayoutClient = view.findViewById(R.id.linearLayoutClient)
        LinearLayoutWallet = view.findViewById(R.id.LinearLayoutWallet)
        LinearLayoutPayouts = view.findViewById(R.id.LinearLayoutPayouts)
        LinearLayoutHelp = view.findViewById(R.id.LinearLayoutHelp)
        linearLayoutAddOrder = view.findViewById(R.id.linearLayoutAddOrder)
        linearLayoutAccountSettings = view.findViewById(R.id.linearLayoutAccountSettings)
        LinearLayoutProductManagement = view.findViewById(R.id.LinearLayoutProductManagement)

        LinearLayoutProductManagement.setOnClickListener {
            (activity as MainActivity).loadFragment(ProductManagementFragment())
        }

        linearLayoutAccountSettings.setOnClickListener {
            (activity as MainActivity).loadFragment(AccountSettingsFragment())
        }

        linearLayoutAddOrder.setOnClickListener {
            (activity as MainActivity).loadFragment(AddOrderFragment())
        }

        LinearLayoutHelp.setOnClickListener {
            (activity as MainActivity).loadFragment(HelpDadabazarFragment())
        }

        LinearLayoutPayouts.setOnClickListener {
            (activity as MainActivity).loadFragment(PayoutsFragment())
        }

        LinearLayoutWallet.setOnClickListener {
            (activity as MainActivity).loadFragment(WalletFragment())
        }

        linearLayoutClient.setOnClickListener {
            (activity as MainActivity).loadFragment(ClientListFragment())
        }

        linearLayoutMyOrders.setOnClickListener {
            (activity as MainActivity).loadFragment(MyOrdersFragment())
        }

        btnBack.setOnClickListener {
            (activity as MainActivity).loadFragment(ProfileUserFragment())
        }

        return view
    }
}