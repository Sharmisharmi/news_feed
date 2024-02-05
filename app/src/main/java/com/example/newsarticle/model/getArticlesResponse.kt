package com.example.newsarticle.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class getArticlesResponse {

    @SerializedName("status")
    @Expose
     val status: String? = null

    @SerializedName("articles")
    @Expose
     val articles: List<getArticleData>? = null
}