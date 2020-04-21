package com.dshovhenia.playgroundapp.data.source.video

import com.dshovhenia.playgroundapp.data.model.video.Video
import com.dshovhenia.playgroundapp.data.source.base.video.VideoCache
import com.dshovhenia.playgroundapp.data.source.base.video.VideoDataStore
import javax.inject.Inject

class VideoCacheDataStore @Inject constructor(private val videoCache: VideoCache) :
  VideoDataStore {

  override fun clearVideos() {
    videoCache.clearVideos()
  }

  override fun saveVideos(videos: List<Video>) {
    videoCache.saveVideos(videos)
    videoCache.setLastCacheTime(System.currentTimeMillis())
  }

  override fun getVideos(): List<Video> {
    return videoCache.getVideos()
  }

  override fun isCached(): Boolean {
    return videoCache.isCached()
  }

  override fun isExpired(): Boolean {
    return videoCache.isExpired()
  }

}