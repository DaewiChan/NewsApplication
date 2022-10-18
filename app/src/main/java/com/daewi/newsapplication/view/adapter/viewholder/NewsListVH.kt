package com.daewi.newsapplication.view.adapter.viewholder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.daewi.newsapplication.R
import com.daewi.newsapplication.base.BaseViewHolder
import com.daewi.newsapplication.common.DateTimeUtil
import com.daewi.newsapplication.data.dto.ArticleDataVO
import com.daewi.newsapplication.delegate.NewsListsDelegate
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


class NewsListVH(
    context: Context,
    itemView: View,
    private var mDelegate: NewsListsDelegate
) : BaseViewHolder<ArticleDataVO>(itemView) {

    var context: Context = context
    var cvCard = itemView.findViewById<CardView>(R.id.cvNews)
    var ivNews = itemView.findViewById<ImageView>(R.id.ivNews)
    var tvAuthor = itemView.findViewById<TextView>(R.id.tvAuthor)
    var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    var tvPublishAt = itemView.findViewById<TextView>(R.id.tvPublishAt)
    var tvSource = itemView.findViewById<TextView>(R.id.tvSource)

    override fun setData(data: ArticleDataVO) {
        mData = data
        var currency: String? = null

        if (data.author != null) {
            tvAuthor?.text = data.author
        } else {
            tvAuthor?.text = "---"
        }

        if (data.source?.name != null) {
            tvSource?.text = data.source?.name
        } else {
            tvSource?.text = "---"
        }

        if (data.title != null) {
            tvTitle?.text = data.title
        } else {
            tvTitle?.text = "---"
        }

        if (data.publishedAt != null) {
            tvPublishAt?.text = DateTimeUtil().convertDateToString(data.publishedAt?:"")
        } else {
            tvPublishAt?.text = "---"
        }


        if (data.urlToImage != null){
            Glide.with(context)
                .load(data.urlToImage)
                .placeholder(R.drawable.default_news)
                .apply(RequestOptions.bitmapTransform( RoundedCorners(24)))
                .into(ivNews)
        }else{
            Glide.with(context)
                .load(R.drawable.default_news)
                .into(ivNews)
        }

        cvCard?.setOnClickListener {
            mDelegate.onTapNews(mData)
        }
    }

    override fun onClick(p0: View?) {

    }

}