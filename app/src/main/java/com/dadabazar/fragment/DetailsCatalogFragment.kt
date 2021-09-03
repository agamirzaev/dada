package com.dadabazar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.adapter.AdapterDetailsCatalog
import com.dadabazar.data.model.prod_category.ResponseCategory
import com.dadabazar.mvp.prod_category.ProdCategoryController
import com.dadabazar.mvp.prod_category.ProdCategoryPresenter

@Suppress("SENSELESS_COMPARISON")
class DetailsCatalogFragment : Fragment(), ProdCategoryController.View {

    private lateinit var textCatalogName: TextView

    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: ProdCategoryPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var noConnection: TextView
    private lateinit var relativeLayoutNotProducts: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details_catalog, container, false)
        val CatgName = arguments?.getString("CatgName")

        textCatalogName = view.findViewById(R.id.textCatalogName)
        progressBar = view.findViewById(R.id.progressBarProdCatalog)
        noConnection = view.findViewById(R.id.noConnectionProdCatalog)
        relativeLayoutNotProducts = view.findViewById(R.id.RelativeLayoutNotProducts)

        val btnBackCatalogFragment = view.findViewById<ImageView>(R.id.btnBackCatalogDetait)
        btnBackCatalogFragment.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@DetailsCatalogFragment)
                .commit()
            (activity as MainActivity).backDetailsCatalogFragment()
        }

        recyclerView = view.findViewById(R.id.recyclerViewCategoryDetails) as RecyclerView
        presenter = ProdCategoryPresenter((view.context.applicationContext as App).dataManager!!)
        presenter.attachView(this@DetailsCatalogFragment)
        presenter.responseProdCategory("" + CatgName.toString(), "2AOmTW")

        textCatalogName.text = CatgName.toString()
        return view
    }

    override fun getProdCategory(category: ArrayList<ResponseCategory?>) {
        val adapter = AdapterDetailsCatalog(category)
        if (adapter != null) {
            recyclerView.adapter = adapter
        }
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun noConnection() {
        progressBar.visibility = View.GONE
        noConnection.visibility = View.VISIBLE
    }

    override fun noCategoryProducts() {
        relativeLayoutNotProducts.visibility = View.VISIBLE
    }


}