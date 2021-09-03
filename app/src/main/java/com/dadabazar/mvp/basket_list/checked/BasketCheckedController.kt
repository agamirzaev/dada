package com.dadabazar.mvp.basket_list.checked

import com.dadabazar.data.model.Checked
import com.dadabazar.data.model.basketlist.Basket
import com.dadabazar.data.model.basketlist.ResponseQty
import com.dadabazar.mvp.MvpView

interface BasketCheckedController : MvpView {
    interface View : MvpView {
        fun getChecked(checked: Checked)
        fun getUpdateQty(responseQty: ResponseQty)
    }

    interface Presenter : MvpView {
        fun responseChecked(key: String, user_id: String, prod_id: String)
        fun responseUpdateQty(key: String, user_id: String, prod_id: String, action: String)
    }
}