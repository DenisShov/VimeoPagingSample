package com.dshovhenia.playgroundapp.data.source.comment

import com.dshovhenia.playgroundapp.data.model.comment.Comment
import com.dshovhenia.playgroundapp.data.source.base.comment.CommentDataStore
import com.dshovhenia.playgroundapp.data.source.base.comment.CommentRemote
import javax.inject.Inject

class CommentRemoteDataStore @Inject constructor(private val commentRemote: CommentRemote) :
  CommentDataStore {

  override fun clearComments() {
    throw UnsupportedOperationException()
  }

  override fun saveComments(comments: List<Comment>) {
    throw UnsupportedOperationException()
  }

  override fun getComments(): List<Comment> {
    return commentRemote.getComments()
  }

  override fun isCached(): Boolean {
    throw UnsupportedOperationException()
  }

  override fun isExpired(): Boolean {
    throw UnsupportedOperationException()
  }

}