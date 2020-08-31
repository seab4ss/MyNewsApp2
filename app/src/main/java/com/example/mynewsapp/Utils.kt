package com.example.mynewsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log


/**
 * Created by Yury on 8/24/2020
 */

class Utils {
    companion object {
        const val TAG = "NewsUtils"
        fun openWebPage(context: Context, url: String) {
            val webPage: Uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, webPage)

            //Note: we could have also emitted EventBus event to Activity
            //and launched web page from there...

            // Need this flag since we don't have activity context
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {
                Log.e(TAG, "openWebPage unable to resolve activity to handle uri $webPage")
            }
        }
    }
}