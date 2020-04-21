package com.dshovhenia.playgroundapp.data.cache

import com.dshovhenia.playgroundapp.data.cache.db.VimeoDatabase
import com.dshovhenia.playgroundapp.data.cache.helper.CommentDbHelper
import com.dshovhenia.playgroundapp.data.cache.mapper.comment.CommentMapper
import com.dshovhenia.playgroundapp.data.cache.preferences.PreferencesHelper
import com.dshovhenia.playgroundapp.data.model.comment.Comment
import com.dshovhenia.playgroundapp.data.source.base.comment.CommentCache
import javax.inject.Inject

class CommentCacheImpl @Inject constructor(
  private val vimeoDatabase: VimeoDatabase, private val commentDbHelper: CommentDbHelper,
  private val commentMapper: CommentMapper, private val preferencesHelper: PreferencesHelper
) : CommentCache {

  private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

  override fun clearComments() {
    vimeoDatabase.commentDao().clearComments()
  }

  override fun saveComments(comments: List<Comment>) {
    commentDbHelper.insertComments(comments.map { commentMapper.mapTo(it)!! })
  }

  override fun getComments(): List<Comment> {
    return vimeoDatabase.commentDao().getComments().map { commentMapper.mapFrom(it)!! }
  }

  override fun isCached(): Boolean {
    return vimeoDatabase.commentDao().getComments().isNotEmpty()
  }

  override fun setLastCacheTime(lastCache: Long) {
    preferencesHelper.commentLastCacheTime = lastCache
  }

  override fun isExpired(): Boolean {
    val currentTime = System.currentTimeMillis()
    val lastUpdateTime = preferencesHelper.commentLastCacheTime
    return currentTime - lastUpdateTime > EXPIRATION_TIME
  }

}