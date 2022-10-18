package com.daewi.newsapplication.data.db

import com.daewi.newsapplication.data.dto.ArticleDataVO

interface DBHelper {

    fun saveNewsData(newsData: List<ArticleDataVO>)

    fun getNewsData(): List<ArticleDataVO>
}