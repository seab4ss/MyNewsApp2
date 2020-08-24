package com.example.mysandbox2.model

import com.example.mynewsapp.model.MediaCatalog
import com.example.mynewsapp.model.NewsCatalog
import com.example.mynewsapp.model.NewsPrefs
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Yury on 8/24/2020
 */
@Singleton
class NewsRepo @Inject constructor(
    private val mNewsApi: NewsApi,
    private val mPrefs: NewsPrefs
) {

    companion object {
        const val TAG = "NewsRepo"
    }



    internal fun doLoadNewsCatalogFromServer(): NewsCatalog? {
        val catalogCall: Call<NewsCatalog> = mNewsApi.newsCatalog("us", NewsApi.API_KEY)
        val catalog = catalogCall.execute().body()
        return catalog
    }

//    fun cacheNewsCatalog(catalog: MediaCatalog) {
//        mPrefs.put(NewsPrefs.NEWS_CATALOG_KEY, Gson().toJson(catalog))
//    }
//
//    fun loadNewsCatalogFromCache(): MediaCatalog {
//        val strCatalog: String? = mPrefs.get(NewsPrefs.NEWS_CATALOG_KEY, "")
//        val catalog: MediaCatalog
//        if (strCatalog.isNullOrBlank()) {
//            catalog = Gson().fromJson(strCatalog, MediaCatalog::class.java)
//        } else {
//            catalog = MediaCatalog.empty()
//        }
//        return catalog
//    }


}


interface NewsApi {
    companion object {
        const val API_KEY = "1437be957ba74f9e93cf1688a28a05ac"
    }

    //    @GET("/sandbox2/mediaCatalog")
//    fun mediaCatalog(): Call<MediaCatalog>
    @GET("top-headlines")
    fun newsCatalog(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsCatalog>

}


