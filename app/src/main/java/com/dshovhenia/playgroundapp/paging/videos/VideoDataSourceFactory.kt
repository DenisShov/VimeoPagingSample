package com.dshovhenia.playgroundapp.paging.videos

import kotlinx.coroutines.CoroutineScope
import com.dshovhenia.playgroundapp.data.DataManager
import com.dshovhenia.playgroundapp.data.model.VimeoVideo
import com.dshovhenia.playgroundapp.paging.base.BaseDataSource
import com.dshovhenia.playgroundapp.paging.base.BaseDataSourceFactory

class VideoDataSourceFactory(
  private val mDataManager: DataManager, private val scope: CoroutineScope
) : BaseDataSourceFactory<VimeoVideo>() {

  private var mInitialUri: String? = null
  private var mSearchQuery: String? = null

  override fun generateDataSource(): BaseDataSource<VimeoVideo> {
    return VideoDataSource(mDataManager, scope, mInitialUri, mSearchQuery)
  }

  fun setInitialUri(uri: String?) {
    mInitialUri = uri
  }

  fun setSearchQuery(query: String?) {
    mSearchQuery = query
  }

}