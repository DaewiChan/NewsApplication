package com.daewi.newsapplication.view.news_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.daewi.newsapplication.R
import com.daewi.newsapplication.common.DateTimeUtil

class NewsDetailsFragment: Fragment() {

    private var tvTitle: TextView? = null
    private var tvDescription: TextView? = null
    private var tvAuthor: TextView? = null
    private var tvTime: TextView? = null
    private var ivNews: ImageView? = null
    private var ivback: ImageView? = null
    private var tvContent: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_news_details,container,false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTitle = view.findViewById(R.id.tvTitle)
        tvDescription = view.findViewById(R.id.tvDescription)
        tvAuthor = view.findViewById(R.id.tvAuthor)
        tvTime = view.findViewById(R.id.tvTime)
        tvContent = view.findViewById(R.id.tvContent)
        ivNews = view.findViewById(R.id.ivDetailsNews)
        ivback = view.findViewById(R.id.ivBack)

        getPara()

        ivback?.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    fun getPara() {
        if (arguments?.getString("title") != null) {
            var title = arguments?.getString("title")
            tvTitle?.text = title
        }

        if (arguments?.getString("description") != null) {
            var description = arguments?.getString("description")
            tvDescription?.text = description
        }

        if (arguments?.getString("author") != null) {
            var author = arguments?.getString("author")
            tvAuthor?.text = author
        }

        if (arguments?.getString("time") != null) {
            var time = arguments?.getString("time")
            tvTime?.text = DateTimeUtil().convertDateToString(time)
        }

        if (arguments?.getString("imageUrl") != null) {
            var imageUrl = arguments?.getString("imageUrl")
            Glide.with(requireContext())
                .load(imageUrl)
                .placeholder(R.drawable.default_news)
                .apply(RequestOptions.bitmapTransform( RoundedCorners(24)))
                .into(ivNews!!)
        }

        if (arguments?.getString("content") != null) {
            var content = arguments?.getString("content")
            tvContent?.text = content
        }
    }

}