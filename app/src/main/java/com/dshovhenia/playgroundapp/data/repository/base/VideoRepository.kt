package com.dshovhenia.playgroundapp.data.repository.base

import com.dshovhenia.playgroundapp.data.model.video.Video

interface VideoRepository {

  fun clearVideos()

  fun saveVideos(videos: List<Video>)

  fun getVideos(url: String?, query: String?, page: Int?, per_page: Int?): List<Video>

}