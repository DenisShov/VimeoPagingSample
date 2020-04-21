package com.dshovhenia.playgroundapp.data.repository

import com.dshovhenia.playgroundapp.data.model.comment.Comment
import com.dshovhenia.playgroundapp.data.repository.base.CommentRepository
import com.dshovhenia.playgroundapp.data.source.comment.CommentCacheDataStore
import com.dshovhenia.playgroundapp.data.source.comment.CommentRemoteDataStore
import javax.inject.Inject

class CommentDataRepository @Inject constructor(
  private val commentCacheDataStore: CommentCacheDataStore,
  private val commentRemoteDataStore: CommentRemoteDataStore
) : CommentRepository {

  override fun clearComments() {
    commentCacheDataStore.clearComments()
  }

  override fun saveComments(comments: List<Comment>) {
    return commentCacheDataStore.saveComments(comments)
  }

  override fun getComments(url: String?, direction: String?, page: Int?, per_page: Int?): List<Comment> {
    var comments: List<Comment> = commentRemoteDataStore.getComments()
    saveComments(comments)
    comments = commentCacheDataStore.getComments()

    // todo fix timestamp
//    if (commentCacheDataStore.isCached() && !commentCacheDataStore.isExpired()) {
//      comments = commentCacheDataStore.getComments()
//    } else {
//      comments = commentRemoteDataStore.getComments()
//      saveComments(comments)
//    }
    return comments
  }

}