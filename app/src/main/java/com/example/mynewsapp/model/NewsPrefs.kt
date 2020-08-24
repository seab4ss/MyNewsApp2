package com.example.mynewsapp.model

import android.content.Context
import android.content.SharedPreferences
import com.example.mynewsapp.NewsApp
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Yury on 8/24/2020
 */
@Singleton
class NewsPrefs @Inject constructor() {
    private val mPrefsFile = "NewsPrefsFile"
    private val mPrefs: SharedPreferences =
        NewsApp.appContext.getSharedPreferences(mPrefsFile, Context.MODE_PRIVATE)
    companion object{
        const val NEWS_CATALOG_KEY = "news_catalog"
    }


    fun put(key: String, value: Boolean) {
        mPrefs.edit().putBoolean(key, value).apply()
    }

    fun get(key: String, defaultValue: Boolean) = mPrefs.getBoolean(key, defaultValue)

    fun put(key: String, value: String) {
        mPrefs.edit().putString(key, value).apply()
    }

    fun get(key: String, defaultValue: String) = mPrefs.getString(key, defaultValue)

    fun contains(key: String) = mPrefs.contains(key)

    fun clearAll() = mPrefs.edit().clear().apply()
}