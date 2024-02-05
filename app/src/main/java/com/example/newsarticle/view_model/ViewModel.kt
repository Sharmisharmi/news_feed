package com.example.newsarticle.view_model

import androidx.lifecycle.MutableLiveData
import com.example.newsarticle.model.getArticlesResponse
import com.example.newsarticle.repository.Repository

class ViewModel {

    var homeRepository:Repository = Repository()

    private var articlesList: MutableLiveData<getArticlesResponse> = MutableLiveData()

    fun getArticlesList(): MutableLiveData<getArticlesResponse> {
        articlesList = homeRepository!!.requestArticlesList()
        return articlesList
    }
}