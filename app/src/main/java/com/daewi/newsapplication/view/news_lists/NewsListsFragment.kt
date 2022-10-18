package com.daewi.newsapplication.view.news_lists

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daewi.newsapplication.R
import com.daewi.newsapplication.common.NetworkUtilities
import com.daewi.newsapplication.data.db.RealmHelper
import com.daewi.newsapplication.data.dto.ArticleDataVO
import com.daewi.newsapplication.data.remote.ApiFactory
import com.daewi.newsapplication.databinding.ActivityMainBinding
import com.daewi.newsapplication.delegate.NewsListsDelegate
import com.daewi.newsapplication.repository.NewsRepository
import com.daewi.newsapplication.view.adapter.NewsListAdapter
import com.daewi.newsapplication.view.adapter.PaginationScrollListener
import com.daewi.newsapplication.view.news_details.NewsDetailsFragment
import com.daewi.newsapplication.viewmodel.NewsListsViewModel
import com.daewi.newsapplication.viewmodel.NewsListsViewModelFactory

class NewsListsFragment: Fragment(),NewsListsDelegate {
    private val TAG = "NewsFragment"

    lateinit var newsListsViewModel: NewsListsViewModel
    private lateinit var mNewsListAdapter: NewsListAdapter

    private var newsDataLists: MutableList<ArticleDataVO> = ArrayList()

    private var rvNews: RecyclerView? = null
    private var etSearch: EditText? = null
    private var ivSearch: ImageView? = null
    private var progressBar: ProgressBar? = null

    var isLoading: Boolean = false
    var isLastPage: Boolean = false
    private var currentPageNO: Int = 1
    private var totalPageNo: Int = 50

    private var searchKey: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_news_lists,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvNews = view.findViewById(R.id.rvNews)
        etSearch = view.findViewById(R.id.etSearch)
        ivSearch = view.findViewById(R.id.ivSearch)
        progressBar = view.findViewById(R.id.progressBar)

        newsListsViewModel = NewsListsViewModel(NewsRepository(ApiFactory),RealmHelper(),progressBar!!)

        bindAdapter()
        if (NetworkUtilities().isConnected(requireContext())){
            loadRemoteData()
        }else{
            loadLocalData()
        }

        updateUI()
        catchEvent()
    }

    private fun bindAdapter(){
        mNewsListAdapter = NewsListAdapter(requireContext(),this)
        val llNews = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        rvNews?.layoutManager = llNews
        rvNews?.adapter = mNewsListAdapter


        rvNews?.setHasFixedSize(true)
        rvNews?.itemAnimator = DefaultItemAnimator()
        rvNews?.addOnScrollListener(object : PaginationScrollListener(llNews) {

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                //you have to call loadmore items to get more data
                getMoreNews()
            }
        })
    }

    fun getMoreNews() {
        isLoading = false

        if (currentPageNO == totalPageNo) {
            isLastPage = true
        }

        currentPageNO++

        if (!isLastPage) {
            progressBar?.visibility = View.GONE
            newsListsViewModel.getNewsLists(searchKey?:"bitcoin",currentPageNO)
        }

    }

    private fun catchEvent(){
        ivSearch?.setOnClickListener {
            if (etSearch?.text.toString().isNotEmpty()){

                clearAdapter()

                searchKey = etSearch?.text.toString()
                currentPageNO = 1
                newsListsViewModel?.getNewsLists(etSearch?.text.toString(),currentPageNO)
            }else{
                etSearch?.error = "Enter search keyword"
                etSearch?.requestFocus()
            }
        }

        etSearch?.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (etSearch?.text.toString().isNotEmpty()){
                    clearAdapter()

                    searchKey = etSearch?.text.toString()
                    currentPageNO = 1
                    newsListsViewModel?.getNewsLists(etSearch?.text.toString(),currentPageNO)
                }
                return@OnEditorActionListener true
            }
            false
        })

       etSearch?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (etSearch?.text.toString().isEmpty()){
                    clearAdapter()
                    loadRemoteData()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {

            }
        })

    }

    fun clearAdapter(){
        newsDataLists?.clear()
        mNewsListAdapter?.setNewData(newsDataLists)
        mNewsListAdapter?.notifyDataSetChanged()
    }

    private fun loadLocalData(){
        newsListsViewModel.getNewsListsFromLocal()
    }

    private fun loadRemoteData(){
        newsListsViewModel.getNewsLists("bitcoin",currentPageNO)
    }

    private fun updateUI(){
        //the observer will only receive events if the owner(activity) is in active state
        //invoked when movieList data changes
        newsListsViewModel.newsLists.observe(viewLifecycleOwner, Observer{
            it.let {
                this.newsDataLists = it as MutableList<ArticleDataVO>
                mNewsListAdapter.appendNewData(newsDataLists)
            }

        })

        Log.e("sdfsaf===>","Success")

        //invoked when a network exception occurred
       // Toast.makeText(requireContext(),"${newsListsViewModel.errorMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onTapNews(data: ArticleDataVO) {
        val bundle = bundleOf("title" to data.title,"description" to data.description,"author" to data.author,"time" to data.publishedAt,"imageUrl" to data.urlToImage,"content" to data.content)
        //var details = NewsDetailsFragment.newInstance(data.title?:"",data.description?:"",data.author?:"",data.publishedAt?:"",data.urlToImage?:"",data.content?:"")
        findNavController().navigate(R.id.viewDetails,bundle)
    }
}
