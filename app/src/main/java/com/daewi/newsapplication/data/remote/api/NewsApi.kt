package com.daewi.newsapplication.data.remote.api

import com.daewi.newsapplication.data.dto.NewsDataResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApi {
    @Headers("Accept: application/json")
    @GET("everything")
    fun getNewsApi(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Observable<NewsDataResponse>
}