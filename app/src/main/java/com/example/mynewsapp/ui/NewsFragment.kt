package com.example.mynewsapp.ui

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mynewsapp.R
import com.example.mynewsapp.model.MediaCatalog
import com.example.mynewsapp.model.NewsCatalog
import com.example.mysandbox2.model.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    companion object {
        const val TAG = "NewsFragment"
        fun newInstance() = NewsFragment()
    }

    private lateinit var mNewsCatalogRefreshLayout: SwipeRefreshLayout
    private lateinit var mNewsCatalogList: RecyclerView
    private val mNewsViewModel: NewsViewModel by viewModels()
//    // use View Binding instead of findViewById()
//    private var _binding: NewsFragmentBinding? = null
//
//    // This property is only valid between onCreateView and onDestroyView.
//    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.news_fragment, container, false)
//        _binding = NewsFragmentBinding.inflate(inflater, container, false)
//        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNewsCatalogRefreshLayout = view.findViewById(R.id.news_catalog_container)
        mNewsCatalogRefreshLayout.setOnRefreshListener { refreshNewsCatalog() }
        mNewsCatalogList = view.findViewById(R.id.news_catalog)
        handleConfigChange(resources.configuration)

        mNewsViewModel.mObservableNewsCatalog.observe(
            viewLifecycleOwner,
            Observer<NewsCatalog> { catalog ->
                if (catalog.articles.isNullOrEmpty()) {
                    Log.w(TAG, "NewsCatalog observer onChanged - empty catalog")
                } else {
                    Log.d(TAG, "MediaCatalog observer onChanged ${catalog.articles.size}")
                    mNewsCatalogList.adapter = NewsAdapter(catalog.articles.toMutableList())
                }
                mNewsCatalogRefreshLayout.isRefreshing = false
            })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        handleConfigChange(newConfig)
    }

    private fun handleConfigChange(newConfig: Configuration) {
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                mNewsCatalogList.layoutManager =
                    GridLayoutManager(context, 2)
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                mNewsCatalogList.layoutManager =
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//                mNewsCatalogList.addItemDecoration(
//                    DividerItemDecoration(
//                        context,
//                        RecyclerView.VERTICAL
//                    )
//                )
            }
            else -> {
                Log.w(TAG, "handleConfigChange unhandled orientation ${newConfig.orientation}")
            }
        }
    }

    private fun refreshNewsCatalog(){

        // Signal SwipeRefreshLayout to start the progress indicator
        //mMediaCatalogRefreshLayout.isRefreshing = true
        mNewsViewModel.refreshNewsCatalog()
    }
}