package com.dshovhenia.playgroundapp.paging.comments

import kotlinx.coroutines.CoroutineScope
import retrofit2.Response
import com.dshovhenia.playgroundapp.data.repository.Repository
import com.dshovhenia.playgroundapp.data.model.Collection
import com.dshovhenia.playgroundapp.data.model.comment.Comment
import com.dshovhenia.playgroundapp.paging.base.BaseDataSource

class CommentsDataSource(
  private val mRepository: Repository,
  private val scope: CoroutineScope,
  private val mInitialUri: String?
) : BaseDataSource<Comment>(scope) {

  override suspend fun getLoadInitialObservable(params: LoadInitialParams<String>): Response<Collection<Comment>> {
    return mRepository.getCommentList(mInitialUri, null, 1, params.requestedLoadSize)
  }

  override suspend fun getLoadAfterObservable(params: LoadParams<String>): Response<Collection<Comment>> {
    return mRepository.getCommentList(params.key, null, null, null)
  }

}