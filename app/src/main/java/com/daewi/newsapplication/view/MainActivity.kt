package com.daewi.newsapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.daewi.newsapplication.R
import com.daewi.newsapplication.common.NetworkUtilities
import com.daewi.newsapplication.data.db.RealmHelper
import com.daewi.newsapplication.data.dto.ArticleDataVO
import com.daewi.newsapplication.data.remote.ApiFactory
import com.daewi.newsapplication.databinding.ActivityMainBinding
import com.daewi.newsapplication.delegate.NewsListsDelegate
import com.daewi.newsapplication.repository.NewsRepository
import com.daewi.newsapplication.view.adapter.NewsListAdapter
import com.daewi.newsapplication.viewmodel.NewsListsViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }
}