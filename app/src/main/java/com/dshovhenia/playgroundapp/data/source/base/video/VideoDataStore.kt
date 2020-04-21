package com.dshovhenia.playgroundapp.data.source.base.video

import com.dshovhenia.playgroundapp.data.model.video.Video

interface VideoDataStore {

  fun clearVideos()

  fun saveVideos(videos: List<Video>)

  fun getVideos(url: String?, query: String?, page: Int?, per_page: Int?): List<Video>

  fun isCached(): Boolean

  fun isExpired(): Boolean

}