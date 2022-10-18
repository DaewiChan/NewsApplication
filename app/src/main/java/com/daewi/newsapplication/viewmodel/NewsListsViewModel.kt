package com.daewi.newsapplication.viewmodel

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daewi.newsapplication.common.JSONUtil
import com.daewi.newsapplication.data.db.RealmHelper
import com.daewi.newsapplication.data.dto.ArticleDataVO
import com.daewi.newsapplication.repository.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class NewsListsViewModel constructor(private val repository: NewsRepository, private val realm: RealmHelper,private val progress: ProgressBar): ViewModel(){

    val newsLists = MutableLiveData<List<ArticleDataVO>>()
    var errorMessage: String = ""
    fun showProgress(){
        progress?.visibility = View.VISIBLE
    }
    fun hideProgress(){
        progress?.visibility = View.GONE
    }

    var subsciption: Disposable? = null

    fun getNewsLists(key: String,page: Int){
        showProgress()
           subsciption = repository.getNewsLists(key,page,50)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
               .doOnTerminate { hideProgress()}
            .subscribe(
                { response ->
                    if (response.status != null){
                        if (response.status.equals("ok")){
                            newsLists.postValue(response.articlesLists!!)
                            realm.saveNewsData(response.articlesLists!!)
                        }else if (response.status.equals("error")){
                            this.errorMessage = response.code?:"MaximumResultReached"
                        }
                    }

                       // view.showBillingList(response)
                },
                { error ->
                    if (error is HttpException) {
                        val errorJsonString = error.response()!!.errorBody()?.string()
                        val errMessage = JSONUtil().parseError(errorJsonString!!)
                        this.errorMessage = errMessage
                        //view.showError(errMessage)
                    } else {
                        val errorMessage = error.message ?: ""
                        this.errorMessage = errorMessage
                    }
                }
            )
    }




    fun getNewsListsFromLocal(){
        newsLists.postValue(realm.getNewsData())
    }
}