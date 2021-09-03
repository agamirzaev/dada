package com.dadabazar.mvp.products_list_trading

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.products.ResponseProducts
import com.dadabazar.mvp.BasePresenter
import com.dadabazar.mvp.products_list.ProductsListTradingController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("SENSELESS_COMPARISON")
class ProductsListTradingPresenter(private val dataManager: DataManager) :
    BasePresenter<ProductsListTradingController.View>(),
    ProductsListTradingController.Presenter {

    private lateinit var call: Call<ResponseProducts>

    override fun responseProductsListTrading(user_id: String,key: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getProductProfile(user_id,key)
            call.enqueue(object : Callback<ResponseProducts> {

                override fun onResponse(
                    call: Call<ResponseProducts>,
                    response: Response<ResponseProducts>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            if (res.getResponse() != null) {
                                it?.getProductsListTrading(res.getResponse()!!)
                            } else {
                                it?.notProducts()
                            }

                        }
                    }
                    Log.e("Products", call.request().toString())
                }

                override fun onFailure(call: Call<ResponseProducts>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("ErrorProducts", call.request().toString())
                }
            })
        }
    }
}