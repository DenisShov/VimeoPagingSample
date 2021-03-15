package com.dshovhenia.playgroundapp.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dshovhenia.playgroundapp.data.cache.model.video.RelationsVideo
import com.dshovhenia.playgroundapp.data.repository.VideoRepository
import com.dshovhenia.playgroundapp.util.Constants

class HomeViewModel @ViewModelInject constructor(
  private val videoRepository: VideoRepository
) : ViewModel() {

  var searchQuery: String? = null

  private val queryLiveData = MutableLiveData<Pair<String, String?>>()
  val videoResult: LiveData<PagingData<RelationsVideo>> = queryLiveData.switchMap { pair ->
    liveData {
      val repos = getResultStream(pair.first, pair.second)
      emitSource(repos)
    }
  }

  fun searchVideos(query: String) {
    searchQuery = query
    clearVideos()
    val pair = Pair(Constants.COLLECTION_URI, searchQuery)
    queryLiveData.postValue(pair)
  }

  fun showStaffPickVideos() {
    val pair = Pair(Constants.STAFF_PICKS_URI, null)
    queryLiveData.postValue(pair)
  }

  fun clearVideos() {
    videoRepository.clearVideos()
  }

  private fun getResultStream(initialUri: String, searchQuery: String?) =
    videoRepository.getVideos(initialUri, searchQuery).cachedIn(viewModelScope)

  companion object {
    const val NETWORK_PAGE_SIZE = 50
  }

}
