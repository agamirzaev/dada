package com.dadabazar.mvp.wishlist_user

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.wishlist.WishlistUser
import com.dadabazar.mvp.BasePresenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishlistUserPresenter(private val dataManager: DataManager) :
    BasePresenter<WishlistUserController.View>(),
    WishlistUserController.Presenter {

    private lateinit var call: Call<WishlistUser>

    override fun responseWishlist(user_id: String,key: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getWishlist(user_id,key)
            call.enqueue(object : Callback<WishlistUser> {

                override fun onResponse(
                    call: Call<WishlistUser>,
                    response: Response<WishlistUser>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            if (res.getResponse() != null) {
                                it?.getWishlist(res.getResponse()!!)
                            } else {
                                it?.notWishlist()
                            }
                        }
                    }
                    Log.e("Wishlist Success", call.request().toString())
                }

                override fun onFailure(call: Call<WishlistUser>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("Wishlist Error", call.request().toString())
                }
            })
        }
    }
}