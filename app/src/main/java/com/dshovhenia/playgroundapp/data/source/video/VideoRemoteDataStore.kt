package com.dshovhenia.playgroundapp.data.source.video

import com.dshovhenia.playgroundapp.data.model.video.Video
import com.dshovhenia.playgroundapp.data.source.base.video.VideoDataStore
import com.dshovhenia.playgroundapp.data.source.base.video.VideoRemote
import javax.inject.Inject

class VideoRemoteDataStore @Inject constructor(private val videoRemote: VideoRemote) :
  VideoDataStore {

  override fun clearVideos() {
    throw UnsupportedOperationException()
  }

  override fun saveVideos(videos: List<Video>) {
    throw UnsupportedOperationException()
  }

  override fun getVideos(url: String?, query: String?, page: Int?, per_page: Int?): List<Video> {
    return videoRemote.getVideos(url, query, page, per_page)
  }

  override fun isCached(): Boolean {
    throw UnsupportedOperationException()
  }

  override fun isExpired(): Boolean {
    throw UnsupportedOperationException()
  }
}