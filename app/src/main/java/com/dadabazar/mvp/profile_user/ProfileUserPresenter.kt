package com.dadabazar.mvp.profile_user

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.Users
import com.dadabazar.data.model.products.ResponseProducts
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileUserPresenter(private val dataManager: DataManager) :
    BasePresenter<ProfileUserControler.View>(),
    ProfileUserControler.Presenter {

    private lateinit var call: Call<Users>
    private lateinit var callProducts: Call<ResponseProducts>

    override fun responseUsers(user_id: String, key: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getUser(user_id, key)
            call.enqueue(object : Callback<Users> {

                override fun onResponse(
                    call: Call<Users>,
                    response: Response<Users>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getUserShow(res)
                        }
                    }
                    Log.e("Products", call.request().toString())
                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("ErrorProducts", call.request().toString())
                }
            })
        }
    }

    override fun responseUsersProducts(user_id: String,key: String) {
        mvpView.let { it ->
            it?.showProgress()
            callProducts = dataManager.getProductProfile(user_id,key)
            callProducts.enqueue(object : Callback<ResponseProducts> {

                override fun onResponse(
                    call: Call<ResponseProducts>,
                    response: Response<ResponseProducts>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            if (res.getResponse() != null) {
                                it?.getProductsShow(res.getResponse()!!)
                            } else {
                                it?.notProductsList()
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