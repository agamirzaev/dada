package com.dadabazar.mvp.profile_seller_products

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.Users
import com.dadabazar.data.model.products.ProductsList
import com.dadabazar.data.model.products.ResponseProducts
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileSellerProductsPresenter(private val dataManager: DataManager) :
    BasePresenter<ProfileSellerProductsController.View>(),
    ProfileSellerProductsController.Presenter {

    private lateinit var call: Call<ResponseProducts>

    private lateinit var callUser: Call<Users>

    override fun responseProductsProfileSeller(key: String, user_id: String, current_id: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getProductProfileSeller(key, user_id, current_id)
            call.enqueue(object : Callback<ResponseProducts> {

                override fun onResponse(
                    call: Call<ResponseProducts>,
                    response: Response<ResponseProducts>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            if (res.getResponse() != null) {
                                it?.getProductsProfileSeller(res.getResponse()!!)
                            } else {
                                it?.nowProductsUserSeller()
                            }
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

    override fun responseProfileSeller(user_id: String, key: String) {
        mvpView.let { it ->
            it?.showProgress()
            callUser = dataManager.getUser(user_id, key)
            callUser.enqueue(object : Callback<Users> {

                override fun onResponse(
                    call: Call<Users>,
                    response: Response<Users>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getProfileSeller(res)
                        }
                    }
                    Log.e("Basket", call.request().toString())
                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("BasketError", call.request().toString())
                }
            })
        }
    }

}