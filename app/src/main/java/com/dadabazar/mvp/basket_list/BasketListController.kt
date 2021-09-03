package com.dadabazar.mvp.basket_list

import com.dadabazar.data.model.basketlist.Basket
import com.dadabazar.mvp.MvpView

interface BasketListController : MvpView {
    interface View : MvpView {
        fun getBasket(basket: ArrayList<Basket>)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
        fun notBasketProducts()
    }

    interface Presenter : MvpView {
        fun responseBasketList(key: String, user_id: String)
    }
}