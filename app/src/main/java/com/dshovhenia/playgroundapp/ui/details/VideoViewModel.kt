package com.dshovhenia.playgroundapp.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideo
import com.dshovhenia.playgroundapp.data.repository.VideoRepository

class VideoViewModel @ViewModelInject constructor(
  private var videoRepository: VideoRepository
) : ViewModel() {

  fun getVideoById(videoId: Long): CachedVideo {
    return videoRepository.getVideoById(videoId)
  }
}
