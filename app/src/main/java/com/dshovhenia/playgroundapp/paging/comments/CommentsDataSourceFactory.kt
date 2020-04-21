package com.dshovhenia.playgroundapp.paging.comments

import kotlinx.coroutines.CoroutineScope
import com.dshovhenia.playgroundapp.data.repository.Repository
import com.dshovhenia.playgroundapp.data.model.comment.Comment
import com.dshovhenia.playgroundapp.paging.base.BaseDataSource
import com.dshovhenia.playgroundapp.paging.base.BaseDataSourceFactory

class CommentsDataSourceFactory(
  private val mRepository: Repository, private val scope: CoroutineScope
) : BaseDataSourceFactory<Comment>() {
  private var mInitialUri: String? = null
  override fun generateDataSource(): BaseDataSource<Comment> {
    return CommentsDataSource(mRepository, scope, mInitialUri)
  }

  fun setInitialUri(uri: String?) {
    mInitialUri = uri
  }

}