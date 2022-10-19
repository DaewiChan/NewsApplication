package com.daewi.newsapplication.domain.remote

import com.daewi.newsapplication.domain.remote.api.NewsApi

object ApiFactory {
    val newsApi: NewsApi = RetrofitService.retrofit(AppConstants.baseUrl).create(NewsApi::class.java)
}