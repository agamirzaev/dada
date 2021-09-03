package com.dadabazar.mvp.register_user

import com.dadabazar.data.model.UserLogin
import com.dadabazar.data.model.UserRegister
import com.dadabazar.mvp.MvpView
import retrofit2.http.Query

interface RegisterUserController : MvpView {
    interface View : MvpView {
        fun getRegisterUser(user: UserRegister)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
    }

    interface Presenter : MvpView {
        fun responseRegisterUser(
            key: String,
            fname: String,
            lname: String,
            uname: String,
            email: String,
            password: String,
            conf_pass: String
        )
    }
}