package com.dadabazar.mvp

interface MvpPresenter<V : MvpView?> {
    fun attachView(view: V)
    fun detachView()
}