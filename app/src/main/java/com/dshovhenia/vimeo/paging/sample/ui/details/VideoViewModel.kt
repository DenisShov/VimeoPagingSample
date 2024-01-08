package com.dshovhenia.vimeo.paging.sample.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.dshovhenia.vimeo.paging.sample.data.cache.model.video.CachedVideo
import com.dshovhenia.vimeo.paging.sample.data.repository.VideoRepository

class VideoViewModel @ViewModelInject constructor(
  private var videoRepository: VideoRepository
) : ViewModel() {

  fun getVideoById(videoId: Long): CachedVideo {
    return videoRepository.getVideoById(videoId)
  }
}
