package com.dshovhenia.playgroundapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dshovhenia.playgroundapp.data.repository.Repository
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideo
import com.dshovhenia.playgroundapp.paging.videos.VideoDataSourceFactory
import javax.inject.Inject

class HomeViewModel @Inject constructor(mRepository: Repository) : ViewModel() {

  private val COLLECTION_URI = "videos"
  private val STAFF_PICKS_URI = "channels/staffpicks/videos"

  var searchQuery: String = ""

  private val dataSourceFactory = VideoDataSourceFactory(
    mRepository, viewModelScope
  )

  internal val cachedVideoListLiveData: LiveData<PagedList<CachedVideo>>
  internal val stateLiveData =
    Transformations.switchMap(dataSourceFactory.collectionDataSourceLiveData) {
      it.stateLiveData
    }

  init {
    dataSourceFactory.setInitialUri(STAFF_PICKS_URI)

    val config = PagedList.Config.Builder().setPageSize(10).setInitialLoadSizeHint(20)
      .setEnablePlaceholders(false).build()
    cachedVideoListLiveData = LivePagedListBuilder(dataSourceFactory, config).build()
  }

  fun searchVideos(query: String) {
    searchQuery = query
    dataSourceFactory.setInitialUri(COLLECTION_URI)
    dataSourceFactory.setSearchQuery(query)
    invalidateDataSource()
  }

  fun showStaffPickVideos() {
    dataSourceFactory.setInitialUri(STAFF_PICKS_URI)
    dataSourceFactory.setSearchQuery(null)
    invalidateDataSource()
  }

  fun retry() {
    dataSourceFactory.collectionDataSourceLiveData.value?.retry()
  }

  fun invalidateDataSource() {
    dataSourceFactory.collectionDataSourceLiveData.value?.invalidate()
  }

}
