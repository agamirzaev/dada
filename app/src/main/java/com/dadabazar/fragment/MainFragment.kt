package com.dadabazar.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dadabazar.App
import com.dadabazar.MainActivity
import com.dadabazar.R
import com.dadabazar.adapter.AdapterImageSlider
import com.dadabazar.adapter.AdapterProductsTrends
import com.dadabazar.data.model.ImageSlider
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.mvp.product_trends.ProductTrendsController
import com.dadabazar.mvp.product_trends.ProductTrendsPresenter
import com.dadabazar.utills.Preferences
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


@Suppress("SENSELESS_COMPARISON")
class MainFragment : Fragment(), ProductTrendsController.View {

    var sliderItemList: ArrayList<ImageSlider> = ArrayList()

    private lateinit var recyclerViewProductsTrends: RecyclerView
    private lateinit var recyclerViewProductsStock: RecyclerView
    private lateinit var productsListPresenter: ProductTrendsPresenter

    private lateinit var searchDada: LinearLayout

    private lateinit var notificationIcon: LinearLayout

    private fun changeFragment(fm: Fragment) {
        val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
        ft.replace(R.id.content, fm)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.commit()
    }

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val clickCatalog = view.findViewById<RelativeLayout>(R.id.click_catalog)
        val sliderView = view.findViewById<SliderView>(R.id.imageSlider)

        productsListPresenter =
            ProductTrendsPresenter((view.context.applicationContext as App).dataManager!!)
        productsListPresenter.attachView(this@MainFragment)
        productsListPresenter.responseProductTrends("2AOmTW", "1099")

        searchDada = view.findViewById(R.id.searchDada)

        searchDada.setOnClickListener {
            (activity as MainActivity).loadFragment(SearchProductFragment())
        }
        recyclerViewProductsTrends = view.findViewById(R.id.recyclerViewProductsTrends)
        recyclerViewProductsStock = view.findViewById(R.id.recyclerViewProductsStock)

        notificationIcon = view.findViewById(R.id.notificationIcon)

        val mGridLayoutManager = GridLayoutManager(view.context, 3)
        recyclerViewProductsTrends.layoutManager = mGridLayoutManager

        val mGridLayoutManagerStock = GridLayoutManager(view.context, 3)
        recyclerViewProductsStock.layoutManager = mGridLayoutManagerStock


        notificationIcon.setOnClickListener {
            if (Preferences.loadUserId(view.context).toString() != "") {
                changeFragment(NotificationFragment())
            } else {
                (activity as MainActivity).alertDialog()
            }
        }

        clickCatalog.setOnClickListener {
            changeFragment(CatalogFragment())
            (activity as MainActivity?)!!.navigationMainFragment()
        }

        sliderItemList.add(ImageSlider("https://dadabazar.online/themes/default/statics/img/slider.jpg"))
        sliderItemList.add(ImageSlider("https://dadabazar.online/themes/default/statics/img/slider-1.jpg"))

        val adapter = AdapterImageSlider(sliderItemList)

        sliderView.setSliderAdapter(adapter)

        sliderView.setIndicatorAnimation(IndicatorAnimationType.NONE)
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderView.indicatorSelectedColor = Color.WHITE
        sliderView.indicatorUnselectedColor = Color.BLACK
        sliderView.scrollTimeInSec = 4
        sliderView.startAutoCycle()


        if (Preferences.loadUserId(view.context).toString() != "") {
            (activity as MainActivity).basketCount(0)
        }

        return view
    }

    override fun getProductTrends(productsList: ArrayList<ProductsList>) {
        val myAdapter = AdapterProductsTrends(productsList, this@MainFragment)
        if (myAdapter != null) {
            recyclerViewProductsTrends.adapter = myAdapter
            recyclerViewProductsStock.adapter = myAdapter
        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun noConnection() {

    }
}

