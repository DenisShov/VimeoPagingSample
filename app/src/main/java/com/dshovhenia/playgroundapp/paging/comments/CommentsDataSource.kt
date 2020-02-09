package com.dshovhenia.playgroundapp.paging.comments

import kotlinx.coroutines.CoroutineScope
import retrofit2.Response
import com.dshovhenia.playgroundapp.data.DataManager
import com.dshovhenia.playgroundapp.data.model.VimeoCollection
import com.dshovhenia.playgroundapp.data.model.VimeoComment
import com.dshovhenia.playgroundapp.paging.base.BaseDataSource

class CommentsDataSource(
  private val mDataManager: DataManager,
  private val scope: CoroutineScope,
  private val mInitialUri: String?
) : BaseDataSource<VimeoComment>(scope) {

  override suspend fun getLoadInitialObservable(params: LoadInitialParams<String>): Response<VimeoCollection<VimeoComment>> {
    return mDataManager.getCommentList(mInitialUri, null, 1, params.requestedLoadSize)
  }

  override suspend fun getLoadAfterObservable(params: LoadParams<String>): Response<VimeoCollection<VimeoComment>> {
    return mDataManager.getCommentList(params.key, null, null, null)
  }

}