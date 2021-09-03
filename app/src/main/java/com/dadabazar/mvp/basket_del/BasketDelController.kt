package com.dadabazar.mvp.basket_del

import com.dadabazar.data.model.basketlist.AddBasket
import com.dadabazar.data.model.basketlist.DelBasket
import com.dadabazar.mvp.MvpView

interface BasketDelController : MvpView {
    interface View : MvpView {
        fun getDelBasketList(delBasket: DelBasket)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
    }

    interface Presenter : MvpView {
        fun responseDelBasket(key: String,user_id: String, prod_id: String)
    }
}