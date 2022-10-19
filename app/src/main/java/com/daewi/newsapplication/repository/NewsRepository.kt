package com.daewi.newsapplication.repository

import com.daewi.newsapplication.domain.remote.ApiFactory

class NewsRepository constructor(private val apiFactory: ApiFactory) {

    fun getNewsLists(key: String,page: Int,pageSize: Int) = apiFactory.newsApi.getNewsApi(key,"7b624cf7f6dd432f8d7bf0ce96804b06",page,pageSize)
}