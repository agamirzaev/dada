package com.dadabazar.mvp.basket_add

import com.dadabazar.data.model.basketlist.AddBasket
import com.dadabazar.mvp.MvpView

interface BasketAddController : MvpView {
    interface View : MvpView {
        fun getAddBasketList(addBasket: AddBasket)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
    }

    interface Presenter : MvpView {
        fun responseAddBasket(key: String,user_id: String, prod_id: String, quantity: Int)
    }
}