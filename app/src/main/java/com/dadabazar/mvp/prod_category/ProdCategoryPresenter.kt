package com.dadabazar.mvp.prod_category

import android.util.Log
import com.dadabazar.data.DataManager
import com.dadabazar.data.model.prod_category.ProdCategory
import com.dadabazar.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdCategoryPresenter(private val dataManager: DataManager) :
    BasePresenter<ProdCategoryController.View>(),
    ProdCategoryController.Presenter {

    private lateinit var call: Call<ProdCategory>

    override fun responseProdCategory(catg_parent: String, key: String) {
        mvpView.let { it ->
            it?.showProgress()
            call = dataManager.getPodCategoryList(catg_parent, key)
            call.enqueue(object : Callback<ProdCategory> {

                override fun onResponse(
                    call: Call<ProdCategory>,
                    response: Response<ProdCategory>
                ) {
                    it?.hideProgress()
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            if (res.getResponse() != null) {
                                it?.getProdCategory(res.getResponse()!!)
                            } else {
                                it?.noCategoryProducts()
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
