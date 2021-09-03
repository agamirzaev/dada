package com.dadabazar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.adapter.AdapterProductTrading
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.mvp.products_list.ProductsListTradingController
import com.dadabazar.mvp.products_list_trading.ProductsListTradingPresenter
import com.dadabazar.utills.Preferences

@Suppress("SENSELESS_COMPARISON")
class ProductManagementFragment : Fragment(), ProductsListTradingController.View {

    private lateinit var btnBack: ImageView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var presenter: ProductsListTradingPresenter

    private lateinit var recyclerViewMyProducts: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trading_panel, container, false)
        btnBack = view.findViewById(R.id.btnBack)
        recyclerViewMyProducts = view.findViewById(R.id.recyclerViewMyProducts)
        linearLayoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recyclerViewMyProducts.layoutManager = linearLayoutManager

        presenter =
            ProductsListTradingPresenter((view.context.applicationContext as App).dataManager!!)
        presenter.attachView(this@ProductManagementFragment)

        presenter.responseProductsListTrading(Preferences.loadUserId(requireContext()).toString(),"2AOmTW")

        btnBack.setOnClickListener {
            (activity as MainActivity).loadFragment(ControlPanelFragment())
        }
        return view
    }

    override fun getProductsListTrading(category: ArrayList<ProductsList>) {
        val adapter = AdapterProductTrading(category)
        if (adapter != null) {
            recyclerViewMyProducts.adapter = adapter
        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun noConnection() {

    }

    override fun notProducts() {

    }
}