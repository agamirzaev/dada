package com.dadabazar.mvp.category_list

import com.dadabazar.data.model.prod_category.ResponseCategory
import com.dadabazar.mvp.MvpView
import retrofit2.http.Query

interface CategoryController : MvpView {
    interface View : MvpView {
        fun getCategory(category: ArrayList<ResponseCategory?>)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
        fun notProducts()
    }

    interface Presenter : MvpView{
        fun responseCategory(key: String)
    }
}