package com.dadabazar.mvp.product_trends

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.data.model.products.ResponseProducts
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductTrendsPresenter(private val dataManager: DataManager) :
    BasePresenter<ProductTrendsController.View>(),
    ProductTrendsController.Presenter {

    private lateinit var call: Call<ResponseProducts>

    override fun responseProductTrends(key: String, user_id: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getProductsTrands(key, user_id)
            call.enqueue(object : Callback<ResponseProducts> {

                override fun onResponse(
                    call: Call<ResponseProducts>,
                    response: Response<ResponseProducts>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getProductTrends(res.getResponse()!!)
                        }
                    }
                    Log.e("Basket", call.request().toString())
                }

                override fun onFailure(call: Call<ResponseProducts>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("BasketError", call.request().toString())
                }
            })
        }
    }
}