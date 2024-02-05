package com.example.newsarticle.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class getArticleData {

    @SerializedName("source")
    @Expose
     val source: SourceData? = null

    @SerializedName("author")
    @Expose
     val author: String? = null

    @SerializedName("title")
    @Expose
     val title: String? = null

    @SerializedName("description")
    @Expose
     val description: String? = null

    @SerializedName("url")
    @Expose
     val url: String? = null

    @SerializedName("urlToImage")
    @Expose
     val urlToImage: String? = null

    @SerializedName("publishedAt")
    @Expose
     val publishedAt: String? = null

    @SerializedName("content")
    @Expose
     val content: String? = null
}