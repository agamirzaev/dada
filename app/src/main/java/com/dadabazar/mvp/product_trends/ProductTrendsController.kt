package com.dadabazar.mvp.product_trends

import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.mvp.MvpView

interface ProductTrendsController : MvpView {
    interface View : MvpView {
        fun getProductTrends(productsList: ArrayList<ProductsList>)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
    }

    interface Presenter : MvpView {
        fun responseProductTrends(key: String,user_id: String)
    }
}