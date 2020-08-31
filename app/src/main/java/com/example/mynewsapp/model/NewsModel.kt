package com.example.mysandbox2.model

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.mynewsapp.model.MediaCatalog
import com.example.mynewsapp.model.NewsCatalog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Created by Yury on 8/24/2020
 */

class NewsViewModel @ViewModelInject constructor(private val mRepo: NewsRepo) : ViewModel() {

    companion object {
        const val TAG = "NewsViewModel"
    }

    //val mObservableNewsCatalog: MutableLiveData<NewsCatalog> = MutableLiveData()

    init {
        //fetchNewsCatalogFromServer()
    }

   // lazy loading of the news catalog
    private val mLazyNewsCatalog by lazy {
        val liveData = MutableLiveData<NewsCatalog>()
        viewModelScope.launch {
            liveData.value = doFetchNewsCatalogFromServer()
        }

        liveData
    }

    fun observableNewsCatalog(): LiveData<NewsCatalog> = mLazyNewsCatalog

    // Make a network request without blocking the UI thread
    private fun fetchNewsCatalogFromServer() {
        // launch a coroutine in viewModelScope
        viewModelScope.launch {
            mLazyNewsCatalog.value = doFetchNewsCatalogFromServer()
        }
    }

    private suspend fun doFetchNewsCatalogFromServer(): NewsCatalog? {
        // Move the execution of the coroutine to the I/O dispatcher
        return withContext(Dispatchers.IO) {
            Log.i(TAG, "doFetchNewsCatalogFromServer")
            mRepo.doLoadNewsCatalogFromServer()
        }
    }

    fun refreshNewsCatalog() {
        Log.i(TAG, "refreshNewsCatalog")
        fetchNewsCatalogFromServer()
    }


}