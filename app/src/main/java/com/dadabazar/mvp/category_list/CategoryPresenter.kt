package com.dadabazar.mvp.category_list

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.prod_category.ProdCategory
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class CategoryPresenter(private val dataManager: DataManager) :
    BasePresenter<CategoryController.View>(),
    CategoryController.Presenter {

    private lateinit var call: Call<ProdCategory>

    override fun responseCategory(key: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getCategoryList(key)
            call.enqueue(object : Callback<ProdCategory> {

                override fun onResponse(
                    call: Call<ProdCategory>,
                    response: Response<ProdCategory>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            if (res.getResponse() != null) {
                                it?.getCategory(res.getResponse()!!)
                            } else {
                                it?.notProducts()
                            }
                        }
                    }
                    Log.e("Logaut", call.request().toString())
                }

                override fun onFailure(call: Call<ProdCategory>, t: Throwable) {
                    it?.hideProgress()
                    it?.noConnection()
                    Log.e("Logaut", call.request().toString())
                }
            })
        }
    }
}