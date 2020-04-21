package com.dshovhenia.playgroundapp.data.cache

import com.dshovhenia.playgroundapp.data.cache.db.VimeoDatabase
import com.dshovhenia.playgroundapp.data.cache.helper.VideoDbHelper
import com.dshovhenia.playgroundapp.data.cache.mapper.video.VideoMapper
import com.dshovhenia.playgroundapp.data.cache.preferences.PreferencesHelper
import com.dshovhenia.playgroundapp.data.model.video.Video
import com.dshovhenia.playgroundapp.data.source.base.video.VideoCache
import javax.inject.Inject

class VideoCacheImpl @Inject constructor(
  private val vimeoDatabase: VimeoDatabase, private val videoDbHelper: VideoDbHelper,
  private val videoMapper: VideoMapper, private val preferencesHelper: PreferencesHelper
) : VideoCache {

  private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

  override fun clearVideos() {
    vimeoDatabase.videoDao().clearVideos()
  }

  override fun saveVideos(videos: List<Video>) {
    videoDbHelper.insertVideos(videos.map { videoMapper.mapTo(it)!! })
  }

  override fun getVideos(): List<Video> {
    return vimeoDatabase.videoDao().getVideos().map { videoMapper.mapFrom(it)!! }
  }

  override fun isCached(): Boolean {
    return vimeoDatabase.videoDao().getVideos().isNotEmpty()
  }

  override fun setLastCacheTime(lastCache: Long) {
    preferencesHelper.videoLastCacheTime = lastCache
  }

  override fun isExpired(): Boolean {
    val currentTime = System.currentTimeMillis()
    val lastUpdateTime = preferencesHelper.videoLastCacheTime
    return currentTime - lastUpdateTime > EXPIRATION_TIME
  }
}