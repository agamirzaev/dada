package com.dadabazar.mvp.basket_list.qty

import com.dadabazar.data.model.basketlist.Basket
import com.dadabazar.data.model.basketlist.ResponseQty
import com.dadabazar.mvp.MvpView

interface QTYController : MvpView {
    interface View : MvpView {
        fun getQTY(responseQty: ResponseQty)
    }

    interface Presenter : MvpView {
        fun responseQTY(key: String, user_id: String, id_prod: String, action: String)
    }
}