package com.example.newsarticle.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsarticle.api.APInterface
import com.example.newsarticle.api.ApiClient
import com.example.newsarticle.model.getArticlesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    fun requestArticlesList(): MutableLiveData<getArticlesResponse> {
        val mutableLiveData: MutableLiveData<getArticlesResponse> = MutableLiveData()


        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.getArticleList()?.enqueue(object :
            Callback<getArticlesResponse?> {
            override fun onResponse(call: Call<getArticlesResponse?>?, response: Response<getArticlesResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )

                    Log.d("response_raw", "onResponse: "+response.raw())



                }else{

                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<getArticlesResponse?>, t: Throwable?) {

                Log.d("response_raw", "onFailure: "+t.toString())



            }
        })

        return mutableLiveData
    }
}