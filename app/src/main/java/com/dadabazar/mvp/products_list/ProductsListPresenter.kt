package com.dadabazar.mvp.products_list

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.products.ResponseProducts
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("SENSELESS_COMPARISON")
class ProductsListPresenter(private val dataManager: DataManager) :
    BasePresenter<ProductsListController.View>(),
    ProductsListController.Presenter {

    private lateinit var call: Call<ResponseProducts>

    override fun responseProductsList(category: String, key: String,user_id:String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getProductsList(category, key,user_id)
            call.enqueue(object : Callback<ResponseProducts> {

                override fun onResponse(
                    call: Call<ResponseProducts>,
                    response: Response<ResponseProducts>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            if (res.getResponse() != null) {
                                it?.getProductsList(res.getResponse()!!)
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