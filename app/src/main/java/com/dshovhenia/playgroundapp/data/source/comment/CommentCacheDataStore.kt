package com.dshovhenia.playgroundapp.data.source.comment

import com.dshovhenia.playgroundapp.data.model.comment.Comment
import com.dshovhenia.playgroundapp.data.source.base.comment.CommentCache
import com.dshovhenia.playgroundapp.data.source.base.comment.CommentDataStore
import javax.inject.Inject

class CommentCacheDataStore @Inject constructor(private val commentCache: CommentCache) :
  CommentDataStore {

  override fun clearComments() {
    return commentCache.clearComments()
  }

  override fun saveComments(comments: List<Comment>) {
    commentCache.saveComments(comments)
    commentCache.setLastCacheTime(System.currentTimeMillis())
  }

  override fun getComments(): List<Comment> {
    return commentCache.getComments()
  }

  override fun isCached(): Boolean {
    return commentCache.isCached()
  }

  override fun isExpired(): Boolean {
    return commentCache.isExpired()
  }

}