package com.dadabazar.mvp.login_user

import com.dadabazar.data.model.UserLogin
import com.dadabazar.mvp.MvpView

interface LoginUserController : MvpView {
    interface View : MvpView {
        fun getLoginUser(user: UserLogin)
        fun showProgress()
        fun hideProgress()
        fun noConnection()
    }

    interface Presenter : MvpView {
        fun responseLoginUser(key: String,email: String, password: String)
    }
}