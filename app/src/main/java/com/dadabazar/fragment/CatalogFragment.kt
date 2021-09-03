package com.dadabazar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.adapter.AdapterCategory
import com.dadabazar.data.model.Language
import com.dadabazar.data.model.prod_category.ResponseCategory
import com.dadabazar.mvp.category_list.CategoryController
import com.dadabazar.mvp.category_list.CategoryPresenter
import com.dadabazar.mvp.language.LanguageController
import com.dadabazar.mvp.language.LanguagePresenter


@Suppress("SENSELESS_COMPARISON", "UNNECESSARY_NOT_NULL_ASSERTION")
class CatalogFragment : Fragment(), CategoryController.View{
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: CategoryPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var noConnection: TextView
    private lateinit var relativeLayoutNotProducts: RelativeLayout

    private var categoryList = ArrayList<ResponseCategory>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)

        progressBar = view.findViewById(R.id.progressBar)
        noConnection = view.findViewById(R.id.noConnection)
        relativeLayoutNotProducts = view.findViewById(R.id.RelativeLayoutNotProducts)

        val btnBackCatalog = view.findViewById<ImageView>(R.id.btnBackCatalog)
        btnBackCatalog.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(this@CatalogFragment)
                .commit()
            (activity as MainActivity).backFragmentCatalog()
        }

        recyclerView = view.findViewById(R.id.recyclerViewCategory) as RecyclerView
        presenter = CategoryPresenter((view.context.applicationContext as App).dataManager!!)
        presenter.attachView(this@CatalogFragment)

        presenter.responseCategory("2AOmTW")

//        recyclerView.apply {
//            setHasFixedSize(true)
//            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
//        }
        return view
    }

    override fun getCategory(category: ArrayList<ResponseCategory?>) {
        for (categoryMain in category) {
            if (categoryMain!!.getStatus() == "active" && categoryMain!!.getPage() == "main" && categoryMain!!.getLevel() == "0") {
                categoryList.add(categoryMain!!)
            }
            val adapter = AdapterCategory(categoryList)
            if (adapter != null) {
                recyclerView.adapter = adapter
            }
        }
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun noConnection() {
        noConnection.visibility = View.VISIBLE
    }

    override fun notProducts() {
        relativeLayoutNotProducts.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this@CatalogFragment)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this@CatalogFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }
}