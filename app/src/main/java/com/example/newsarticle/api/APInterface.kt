package com.example.newsarticle.api

import com.example.newsarticle.model.getArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APInterface {

    @GET("news-api-feed/staticResponse.json")
    fun getArticleList(): Call<getArticlesResponse?>?



}