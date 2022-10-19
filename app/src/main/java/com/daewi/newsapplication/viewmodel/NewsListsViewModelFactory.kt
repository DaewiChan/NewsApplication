package com.daewi.newsapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.daewi.newsapplication.domain.db.RealmHelper
import com.daewi.newsapplication.repository.NewsRepository

class NewsListsViewModelFactory constructor(private val repository: NewsRepository,private val realm: RealmHelper): ViewModel() {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return if (modelClass.isAssignableFrom(NewsListsViewModel::class.java)) {
//            NewsListsViewModel(this.repository,this.realm) as T
//        } else {
//            throw IllegalArgumentException("ViewModel Not Found")
//        }
//    }
}