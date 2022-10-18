package com.daewi.newsapplication.repository

import com.daewi.newsapplication.data.remote.ApiFactory

class NewsRepository constructor(private val apiFactory: ApiFactory) {
    //var apiFactory = ApiFactory.newsApi

    fun getNewsLists(key: String,page: Int,pageSize: Int) = apiFactory.newsApi.getNewsApi(key,"54c056aac04a4f59a48b6c7af7171a71",page,pageSize)
}