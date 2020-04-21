package com.dshovhenia.playgroundapp.data.source.base.video

import com.dshovhenia.playgroundapp.data.model.video.Video

interface VideoCache {

  /**
   * Clear all Video from the cache.
   */
  fun clearVideos()

  /**
   * Save a given list of Video to the cache.
   */
  fun saveVideos(videos: List<Video>)

  /**
   * Retrieve a list of Video, from the cache.
   */
  fun getVideos(): List<Video>

  /**
   * Check whether there is a list of Video stored in the cache.
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