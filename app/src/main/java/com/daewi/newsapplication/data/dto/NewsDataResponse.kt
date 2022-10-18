package com.daewi.newsapplication.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class NewsDataResponse(
    @Json(name = "status")
    var status: String? = null,
    @Json(name = "code")
    var code: String? = null,
    @Json(name = "articles")
    var articlesLists: List<ArticleDataVO>? = null
)
//open class NewsDataResponse {
//    @Json(name = "status")
//    val status: String? = null
//    @Json(name = "articles")
//    val articlesLists: List<ArticleDataVO>? = null
//}

//{
//    "status": "ok",
//    "totalResults": 6537,
//    -"articles": [
//    -{
//        -"source": {
//        "id": "the-verge",
//        "name": "The Verge"
//    },
//        "author": "Richard Lawler",
//        "title": "Former Uber security chief found guilty of covering up massive 2016 data breach",
//        "description": "Uber paid two hackers $100,000 in Bitcoin to keep a 2016 data breach quiet, and now a jury has convicted former chief security officer Joe Sullivan on two charges for not reporting the incident to authorities.",
//        "url": "https://www.theverge.com/2022/10/5/23390063/uber-security-chief-convicted-hack-cover-up-bounty-payment",
//        "urlToImage": "https://cdn.vox-cdn.com/thumbor/lh2YBh8cWvn3ARrenXxjolBte4o=/0x0:2040x1360/1200x628/filters:focal(1020x680:1021x681)/cdn.vox-cdn.com/uploads/chorus_asset/file/23932655/acastro_STK106__01.jpg",
//        "publishedAt": "2022-10-06T00:25:32Z",
//        "content": "Former Uber security chief found guilty of covering up massive 2016 data breach\r\nFormer Uber security chief found guilty of covering up massive 2016 data breach\r\n / Prosecutors claimed Joe Sullivan h… [+4358 chars]"
//    }]