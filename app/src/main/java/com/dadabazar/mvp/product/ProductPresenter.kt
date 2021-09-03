package com.dadabazar.mvp.product

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.Users
import com.dadabazar.data.model.products.ResponseProducts
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductPresenter(private val dataManager: DataManager) :
    BasePresenter<ProductController.View>(),
    ProductController.Presenter {

    private lateinit var call: Call<ResponseProducts>
    private lateinit var callUser: Call<Users>

    override fun responseProductId(id_product: String,key: String,user_id: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getProductId(id_product,key,user_id)
            call.enqueue(object : Callback<ResponseProducts> {

                override fun onResponse(
                    call: Call<ResponseProducts>,
                    response: Response<ResponseProducts>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getProduct(res.getResponse()!!)
                        }
                    }
                    Log.e("productSuccess", call.request().toString())
                }

                override fun onFailure(call: Call<ResponseProducts>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("productError", call.request().toString())
                }
            })
        }
    }

    override fun responseProductUser(user_id: String,key: String) {
        mvpView.let { it ->
            it?.showProgress()
            callUser = dataManager.getUser(user_id,key)
            callUser.enqueue(object : Callback<Users> {

                override fun onResponse(
                    call: Call<Users>,
                    response: Response<Users>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getUserProduct(res)
                        }
                    }
                    Log.e("productSuccess", call.request().toString())
                }

                override fun onFailure(
                    call: Call<Users>,
                    t: Throwable
                ) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("productError", call.request().toString())
                }
            })
        }
    }

}