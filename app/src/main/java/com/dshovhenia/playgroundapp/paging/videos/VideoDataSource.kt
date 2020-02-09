package com.dshovhenia.playgroundapp.paging.videos

import kotlinx.coroutines.CoroutineScope
import retrofit2.Response
import com.dshovhenia.playgroundapp.data.DataManager
import com.dshovhenia.playgroundapp.data.model.VimeoCollection
import com.dshovhenia.playgroundapp.data.model.VimeoVideo
import com.dshovhenia.playgroundapp.paging.base.BaseDataSource

class VideoDataSource(
  private val mDataManager: DataManager,
  private val scope: CoroutineScope,
  private val mInitialUri: String?,
  private val mSearchQuery: String?
) : BaseDataSource<VimeoVideo>(scope) {

  override suspend fun getLoadInitialObservable(params: LoadInitialParams<String>): Response<VimeoCollection<VimeoVideo>> {
    return mDataManager.getVideoList(mInitialUri, mSearchQuery, 1, params.requestedLoadSize)
  }

  override suspend fun getLoadAfterObservable(params: LoadParams<String>): Response<VimeoCollection<VimeoVideo>> {
    return mDataManager.getVideoList(params.key, null, null, null)
  }

}