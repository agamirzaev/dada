package com.dadabazar.mvp.basket_del

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.basketlist.AddBasket
import com.dadabazar.data.model.basketlist.DelBasket
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketDelPresenter(private val dataManager: DataManager) :
    BasePresenter<BasketDelController.View>(),
    BasketDelController.Presenter {

    private lateinit var call: Call<DelBasket>

    override fun responseDelBasket(key: String, user_id: String, prod_id: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getDelBasketList(key, user_id, prod_id)
            call.enqueue(object : Callback<DelBasket> {

                override fun onResponse(
                    call: Call<DelBasket>,
                    response: Response<DelBasket>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getDelBasketList(res)
                        }
                    }
                    Log.e("Products", call.request().toString())
                }

                override fun onFailure(call: Call<DelBasket>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("ErrorProducts", call.request().toString())
                }
            })
        }
    }
}