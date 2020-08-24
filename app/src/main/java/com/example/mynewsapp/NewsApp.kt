package com.example.mynewsapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Yury on 8/14/2020
 */
@HiltAndroidApp
class NewsApp : Application() {


    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {

        lateinit var appContext: Context
            private set
    }

}