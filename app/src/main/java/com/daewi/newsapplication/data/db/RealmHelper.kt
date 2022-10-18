package com.daewi.newsapplication.data.db

import android.util.Log
import com.daewi.newsapplication.data.dto.ArticleDataVO
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmHelper: DBHelper {
    private val mRealm: Realm

    companion object {
        private val DB_NAME = "news.realm"
    }

    init {
        mRealm = Realm.getInstance(
            RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build()
        )
        Log.d("REALM_PATH", "path: " + mRealm.path)
    }

    override fun saveNewsData(newsData: List<ArticleDataVO>) {
        mRealm.executeTransaction { realm -> realm.copyToRealmOrUpdate(newsData) }
    }

    override fun getNewsData(): List<ArticleDataVO> {
        val bean = mRealm.where(ArticleDataVO::class.java).findAll()
        return mRealm.copyFromRealm(bean)
    }
}