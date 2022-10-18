package com.daewi.newsapplication.application

import android.app.Application
import io.realm.Realm

class NewsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }

}