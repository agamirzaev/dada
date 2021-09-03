package com.dadabazar.mvp.basket_list.qty

import android.util.Log
import com.dadabazar.data.DataManager

import com.dadabazar.data.model.basketlist.ResponseBasket
import com.dadabazar.data.model.basketlist.ResponseQty
import com.dadabazar.mvp.BasePresenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QTYPresenter(private val dataManager: DataManager) :
    BasePresenter<QTYController.View>(),
    QTYController.Presenter {

    private lateinit var call: Call<ResponseQty>
    override fun responseQTY(key: String, user_id: String, id_prod: String, action: String) {
        mvpView.let { it ->
            call = dataManager.getUpdateQty(key, user_id, id_prod, action)
            call.enqueue(object : Callback<ResponseQty> {

                override fun onResponse(
                    call: Call<ResponseQty>,
                    response: Response<ResponseQty>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getQTY(res)

                        }
                    }
                    Log.e("Basket", call.request().toString())
                }

                override fun onFailure(call: Call<ResponseQty>, t: Throwable) {
                    Log.e("BasketError", call.request().toString())
                }
            })
        }
    }


}