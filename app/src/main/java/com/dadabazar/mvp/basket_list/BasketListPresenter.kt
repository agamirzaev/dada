package com.dadabazar.mvp.basket_list

import android.util.Log
import com.dadabazar.data.DataManager

import com.dadabazar.data.model.basketlist.ResponseBasket
import com.dadabazar.mvp.BasePresenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BasketListPresenter(private val dataManager: DataManager) :
    BasePresenter<BasketListController.View>(),
    BasketListController.Presenter {

    private lateinit var call: Call<ResponseBasket>

    override fun responseBasketList(key: String, user_id: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getBasketList(key, user_id)
            call.enqueue(object : Callback<ResponseBasket> {

                override fun onResponse(
                    call: Call<ResponseBasket>,
                    response: Response<ResponseBasket>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            if (res.getResponseBasket() != null) {
                                it?.getBasket(res.getResponseBasket()!!)
                            } else {
                                it?.notBasketProducts()
                            }
                        }
                    }
                    Log.e("Basket", call.request().toString())
                }

                override fun onFailure(call: Call<ResponseBasket>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("BasketError", call.request().toString())
                }
            })
        }
    }

}