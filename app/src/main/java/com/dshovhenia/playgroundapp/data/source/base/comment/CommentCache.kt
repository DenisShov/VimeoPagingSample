package com.dshovhenia.playgroundapp.data.source.base.comment

import com.dshovhenia.playgroundapp.data.model.comment.Comment

interface CommentCache {

  /**
   * Clear all Comments from the cache.
   */
  fun clearComments()

  /**
   * Save a given list of Comments to the cache.
   */
  fun saveComments(comments: List<Comment>)

  /**
   * Retrieve a list of Comments, from the cache.
   */
  fun getComments(): List<Comment>

  /**
   * Check whether there is a list of Comments stored in the cache.
   *
   * @return true if the list is cached, otherwise false
   */
  fun isCached(): Boolean

  /**
   * Set a point in time at when the cache was last updated.
   *
   * @param lastCache the point in time at when the cache was last updated
   */
  fun setLastCacheTime(lastCache: Long)

  /**
   * Check if the cache is expired.
   *
   * @return true if the cache is expired, otherwise false
   */
  fun isExpired(): Boolean

}