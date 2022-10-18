package com.daewi.newsapplication.data.remote

import com.daewi.newsapplication.data.remote.api.NewsApi

object ApiFactory {
    val newsApi: NewsApi = RetrofitService.retrofit(AppConstants.baseUrl).create(NewsApi::class.java)
}