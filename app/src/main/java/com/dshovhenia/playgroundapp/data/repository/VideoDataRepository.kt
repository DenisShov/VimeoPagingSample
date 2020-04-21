package com.dshovhenia.playgroundapp.data.repository

import com.dshovhenia.playgroundapp.data.model.video.Video
import com.dshovhenia.playgroundapp.data.repository.base.VideoRepository
import com.dshovhenia.playgroundapp.data.source.video.VideoCacheDataStore
import com.dshovhenia.playgroundapp.data.source.video.VideoRemoteDataStore
import javax.inject.Inject

class VideoDataRepository @Inject constructor(
  private val videoCacheDataStore: VideoCacheDataStore,
  private val videoRemoteDataStore: VideoRemoteDataStore
) : VideoRepository {

  override fun clearVideos() {
    videoCacheDataStore.clearVideos()
  }

  override fun saveVideos(videos: List<Video>) {
    return videoCacheDataStore.saveVideos(videos)
  }

  override fun getVideos(url: String?, query: String?, page: Int?, per_page: Int?): List<Video> {
    var videos = videoRemoteDataStore.getVideos()
    saveVideos(videos)
    videos = videoCacheDataStore.getVideos()

    // todo fix timestamp
//    if (videoCacheDataStore.isCached() && !videoCacheDataStore.isExpired()) {
//      videos = videoCacheDataStore.getVideos()
//    } else {
//      videos = videoRemoteDataStore.getVideos()
//      saveVideos(videos)
//    }
    return videos
  }

}