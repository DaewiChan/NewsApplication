package com.daewi.newsapplication.view.adapter

import android.content.Context
import android.view.ViewGroup
import com.daewi.newsapplication.R
import com.daewi.newsapplication.base.BaseRecyclerAdapter
import com.daewi.newsapplication.domain.dto.ArticleDataVO
import com.daewi.newsapplication.delegate.NewsListsDelegate
import com.daewi.newsapplication.view.adapter.viewholder.NewsListVH

class NewsListAdapter(
    context: Context,
    private var mDelegate: NewsListsDelegate
) : BaseRecyclerAdapter<NewsListVH, ArticleDataVO>(context) {
    var context: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListVH {
        val newsItemView =
            mLayoutInflator.inflate(R.layout.item_news, parent, false)
        return NewsListVH(context, newsItemView,mDelegate)
    }
}