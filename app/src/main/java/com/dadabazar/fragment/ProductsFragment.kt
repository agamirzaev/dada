package com.dadabazar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.adapter.AdapterProducts
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.mvp.products_list.ProductsListController
import com.dadabazar.mvp.products_list.ProductsListPresenter
import com.dadabazar.mvp.products_list.ProductsListTradingController
import com.dadabazar.mvp.products_list_trading.ProductsListTradingPresenter
import com.dadabazar.utills.Preferences


@Suppress("SENSELESS_COMPARISON")
class ProductsFragment : Fragment(), ProductsListController.View {

    private lateinit var productsListPresenter: ProductsListPresenter
    private lateinit var recyclerViewProducts: RecyclerView
    private lateinit var textNameProducts: TextView
    private lateinit var btnBackCatalog: ImageView
    private lateinit var categoryName: String
    private lateinit var titleNotProducts: TextView
    private lateinit var notProductsText: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var notConnection: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        val category = arguments?.getString("CatgId")
        categoryName = arguments?.getString("CatgName").toString()
        recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts) as RecyclerView
        textNameProducts = view.findViewById(R.id.textNameProducts)
        btnBackCatalog = view.findViewById(R.id.btnBackCatalog)

        titleNotProducts = view.findViewById(R.id.titleNotProducts)
        notProductsText = view.findViewById(R.id.textNotProducts)

        progressBar = view.findViewById(R.id.progressBarProducts)
        notConnection = view.findViewById(R.id.notInternet)

        productsListPresenter =
            ProductsListPresenter((view.context.applicationContext as App).dataManager!!)
        productsListPresenter.attachView(this@ProductsFragment)
        productsListPresenter.responseProductsList(
            category.toString(),
            "2AOmTW",
            Preferences.loadUserId(requireContext()).toString()
        )


        btnBackCatalog.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@ProductsFragment)
                .commit()
            (activity as MainActivity).backDetailsCatalogFragment()
        }

        val mGridLayoutManager = GridLayoutManager(view.context, 2)
        recyclerViewProducts.layoutManager = mGridLayoutManager
        return view
    }


    override fun getProductsList(category: ArrayList<ProductsList>) {
        val myAdapter = AdapterProducts(category)
        if (myAdapter != null) {
            recyclerViewProducts.adapter = myAdapter
            textNameProducts.text = categoryName
        }
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun noConnection() {
        notConnection.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun notProducts() {
        notProductsText.visibility = View.VISIBLE
        titleNotProducts.visibility = View.VISIBLE
    }
}