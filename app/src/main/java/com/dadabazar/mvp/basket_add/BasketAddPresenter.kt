package com.dadabazar.mvp.basket_add

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.basketlist.AddBasket
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketAddPresenter(private val dataManager: DataManager) :
    BasePresenter<BasketAddController.View>(),
    BasketAddController.Presenter {

    private lateinit var call: Call<AddBasket>

    override fun responseAddBasket(key: String,user_id: String, prod_id: String, quantity: Int) {
        mvpView.let { it ->
            call = dataManager.getAddBasketList(key,user_id, prod_id, quantity)
            call.enqueue(object : Callback<AddBasket> {

                override fun onResponse(
                    call: Call<AddBasket>,
                    response: Response<AddBasket>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getAddBasketList(res)
                        }
                    }
                    Log.e("Products", call.request().toString())
                }

                override fun onFailure(call: Call<AddBasket>, t: Throwable) {
                    Log.e("ErrorProducts", call.request().toString())
                }
            })
        }
    }
}