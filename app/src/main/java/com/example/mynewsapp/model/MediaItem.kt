package com.example.mynewsapp.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Yury on 8/24/2020
 */

data class MediaItem(
    val title: String,
    val author: String,
    val image: String,
    val description: String,
    val type: String,
    val url: String
) {
    fun hasImageUrl() = image.isNotBlank()
}

data class MediaCatalog(
    val title: String,
    val items: List<MediaItem>
) {
    companion object {
        fun empty(): MediaCatalog {
            return MediaCatalog("Empty Media Catalog", emptyList())
        }
    }
}

data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    @SerializedName("urlToImage")
    val imageUrl: String
)

data class NewsCatalog(
    val status: String,
    val articles: List<Article>
) {
    companion object {
        fun empty(): NewsCatalog {
            return NewsCatalog("No content", emptyList())
        }
    }
}