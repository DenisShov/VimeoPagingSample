package com.dshovhenia.playgroundapp.paging.videos

import kotlinx.coroutines.CoroutineScope
import com.dshovhenia.playgroundapp.data.repository.Repository
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideo
import com.dshovhenia.playgroundapp.paging.base.BaseDataSource
import com.dshovhenia.playgroundapp.paging.base.BaseDataSourceFactory

class VideoDataSourceFactory(
  private val mRepository: Repository, private val scope: CoroutineScope
) : BaseDataSourceFactory<CachedVideo>() {

  private var mInitialUri: String? = null
  private var mSearchQuery: String? = null

  override fun generateDataSource(): BaseDataSource<CachedVideo> {
    return VideoDataSource(mRepository, scope, mInitialUri, mSearchQuery)
  }

  fun setInitialUri(uri: String?) {
    mInitialUri = uri
  }

  fun setSearchQuery(query: String?) {
    mSearchQuery = query
  }

}