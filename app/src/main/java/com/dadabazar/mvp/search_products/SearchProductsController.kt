package com.dadabazar.mvp.search_products

import com.dadabazar.data.model.searchProducts.ProductsSearch
import com.dadabazar.mvp.MvpView

interface SearchProductsController {
    interface View : MvpView {
        fun getProductsSearchList(products: ArrayList<ProductsSearch>)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
        fun noSearchProducs()
    }

    interface Presenter : MvpView {
        fun responseProductsList(key: String,name: String)
    }
}