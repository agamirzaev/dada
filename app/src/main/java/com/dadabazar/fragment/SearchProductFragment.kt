package com.dadabazar.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.adapter.AdapterSearch
import com.dadabazar.data.model.searchProducts.ProductsSearch
import com.dadabazar.mvp.search_products.SearchProductsController
import com.dadabazar.mvp.search_products.SearchProductsPresenter
import com.google.android.material.textfield.TextInputEditText


@Suppress("SENSELESS_COMPARISON")
class SearchProductFragment : Fragment(), SearchProductsController.View {

    private lateinit var searchPresenter: SearchProductsPresenter

    private lateinit var btnBackCatalog: ImageView
    private lateinit var searchProducts: TextInputEditText
    private lateinit var searchProductsView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_product, container, false)
        btnBackCatalog = view.findViewById(R.id.btnBackCatalog)
        searchProducts = view.findViewById(R.id.searchProducts)
        searchProductsView = view.findViewById(R.id.searchProductsView)

        searchPresenter =
            SearchProductsPresenter((view.context.applicationContext as App).dataManager!!)
        searchPresenter.attachView(this@SearchProductFragment)

        btnBackCatalog.setOnClickListener {
            (activity as MainActivity).loadFragment(MainFragment())
        }

        searchProducts.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                searchProducts(s.toString())
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                searchProducts(s.toString())
            }

            override fun afterTextChanged(s: Editable) {
                searchProducts(s.toString())
            }
        })
        return view
    }

    private fun searchProducts(search: String): String {
        if (searchProducts.toString() != "") {
            searchPresenter.responseProductsList("2AOmTW", search)
            searchProductsView.visibility = View.VISIBLE
        } else {
            searchProductsView.visibility = View.GONE
        }
        return search
    }

    override fun getProductsSearchList(products: ArrayList<ProductsSearch>) {
        val myAdapter = AdapterSearch(products, this@SearchProductFragment)
        if (myAdapter != null) {
            searchProductsView.adapter = myAdapter
            searchProductsView.visibility = View.VISIBLE
        } else {
            searchProductsView.visibility = View.GONE
        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun noConnection() {

    }

    override fun noSearchProducs() {
        searchProductsView.visibility = View.GONE
    }

}