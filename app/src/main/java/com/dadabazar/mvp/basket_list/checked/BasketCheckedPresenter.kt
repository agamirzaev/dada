package com.dadabazar.mvp.basket_list.checked

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.Checked

import com.dadabazar.data.model.basketlist.ResponseBasket
import com.dadabazar.data.model.basketlist.ResponseQty
import com.dadabazar.mvp.BasePresenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BasketCheckedPresenter(private val dataManager: DataManager) :
    BasePresenter<BasketCheckedController.View>(),
    BasketCheckedController.Presenter {

    private lateinit var call: Call<Checked>
    private lateinit var callQty: Call<ResponseQty>

    override fun responseChecked(key: String, user_id: String, prod_id: String) {
        mvpView.let { it ->
            call = dataManager.getChecked(key, user_id, prod_id)
            call.enqueue(object : Callback<Checked> {

                override fun onResponse(
                    call: Call<Checked>,
                    response: Response<Checked>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getChecked(res)
                        }
                    }
                    Log.e("Basket", call.request().toString())
                }

                override fun onFailure(call: Call<Checked>, t: Throwable) {
                    Log.e("BasketError", call.request().toString())
                }
            })
        }
    }

    override fun responseUpdateQty(key: String, user_id: String, prod_id: String, action: String) {
        mvpView.let { it ->
            callQty = dataManager.getUpdateQty(key, user_id, prod_id, action)
            callQty.enqueue(object : Callback<ResponseQty> {

                override fun onResponse(
                    call: Call<ResponseQty>,
                    response: Response<ResponseQty>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getUpdateQty(res)
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