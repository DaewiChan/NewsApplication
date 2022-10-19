package com.daewi.newsapplication.viewmodel

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daewi.newsapplication.domain.db.RealmHelper
import com.daewi.newsapplication.domain.dto.ArticleDataVO
import com.daewi.newsapplication.repository.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class NewsListsViewModel constructor(private val repository: NewsRepository, private val realm: RealmHelper,private val progress: ProgressBar,private val context: Context): ViewModel(){

    val newsLists = MutableLiveData<List<ArticleDataVO>>()

    fun showProgress(){
        progress.visibility = View.VISIBLE
    }
    fun hideProgress(){
        progress.visibility = View.GONE
    }

    fun showError(message: String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
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
                        }
                    }
                },
                { error ->
                    if (error is HttpException) {
                        val errorJsonString = error.response()!!.errorBody()?.string()
                        val jsonObject = JSONObject(errorJsonString)
                        if (jsonObject.has("code")){
                          var  errorMessage = jsonObject.getString("code")
                            showError(errorMessage)
                        }
                    } else {
                        val errorMessage = error.message ?: ""
                        if (errorMessage != ""){
                            showError(errorMessage)
                        }
                    }
                }
            )
    }




    fun getNewsListsFromLocal(){
        newsLists.postValue(realm.getNewsData())
    }
}