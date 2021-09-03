package com.dadabazar.mvp.register_user

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.UserRegister
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterUserPresenter(private val dataManager: DataManager) :
    BasePresenter<RegisterUserController.View>(),
    RegisterUserController.Presenter {

    private lateinit var call: Call<UserRegister>

    override fun responseRegisterUser(
        key: String,
        fname: String,
        lname: String,
        uname: String,
        email: String,
        password: String,
        conf_pass: String
    ) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getRegisterUser(key,fname, lname, uname, email, password, conf_pass)
            call.enqueue(object : Callback<UserRegister> {

                override fun onResponse(
                    call: Call<UserRegister>,
                    response: Response<UserRegister>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            it?.getRegisterUser(res)
                        }
                    }
                    Log.e("Register Success", call.request().toString())
                }

                override fun onFailure(call: Call<UserRegister>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("Register Error", call.request().toString())
                }
            })
        }
    }
}