package com.daewi.newsapplication.delegate

import com.daewi.newsapplication.data.dto.ArticleDataVO


interface NewsListsDelegate {
    fun onTapNews(data: ArticleDataVO)
}