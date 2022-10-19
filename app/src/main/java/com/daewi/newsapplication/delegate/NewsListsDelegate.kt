package com.daewi.newsapplication.delegate

import com.daewi.newsapplication.domain.dto.ArticleDataVO


interface NewsListsDelegate {
    fun onTapNews(data: ArticleDataVO)
}