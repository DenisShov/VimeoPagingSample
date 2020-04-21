package com.dshovhenia.playgroundapp.paging.videos

import com.dshovhenia.playgroundapp.data.repository.Repository
import com.dshovhenia.playgroundapp.data.model.Collection
import com.dshovhenia.playgroundapp.data.model.video.Video
import com.dshovhenia.playgroundapp.paging.base.BaseDataSource
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response

class VideoDataSource(
  private val mRepository: Repository,
  private val scope: CoroutineScope,
  private val mInitialUri: String?,
  private val mSearchQuery: String?
) : BaseDataSource<Video>(scope) {

  override suspend fun getLoadInitialObservable(params: LoadInitialParams<String>): Response<Collection<Video>> {
    return mRepository.getVideoList(mInitialUri, mSearchQuery, 1, params.requestedLoadSize)
  }

  override suspend fun getLoadAfterObservable(params: LoadParams<String>): Response<Collection<Video>> {
    return mRepository.getVideoList(params.key, null, null, null)
  }

}