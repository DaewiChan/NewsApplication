package com.daewi.newsapplication.domain.db

import com.daewi.newsapplication.domain.dto.ArticleDataVO

interface DBHelper {

    fun saveNewsData(newsData: List<ArticleDataVO>)

    fun getNewsData(): List<ArticleDataVO>
}