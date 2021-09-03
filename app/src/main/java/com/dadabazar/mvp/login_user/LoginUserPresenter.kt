package com.dadabazar.mvp.login_user

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.UserLogin
import com.dadabazar.mvp.BasePresenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginUserPresenter(private val dataManager: DataManager) :
    BasePresenter<LoginUserController.View>(),
    LoginUserController.Presenter {

    private lateinit var call: Call<UserLogin>

    override fun responseLoginUser(key: String, email: String, password: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getLoginUser(key, email, password)
            call.enqueue(object : Callback<UserLogin> {

                override fun onResponse(
                    call: Call<UserLogin>,
                    response: Response<UserLogin>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getLoginUser(res)
                        }
                    }
                    Log.e("Products", call.request().toString())
                }

                override fun onFailure(call: Call<UserLogin>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("ErrorProducts", call.request().toString())
                }
            })
        }
    }
}