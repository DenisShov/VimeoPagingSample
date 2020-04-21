package com.dshovhenia.playgroundapp.data.remote

import com.dshovhenia.playgroundapp.data.model.video.Video
import com.dshovhenia.playgroundapp.data.remote.service.VimeoApiService
import com.dshovhenia.playgroundapp.data.source.base.video.VideoRemote
import javax.inject.Inject

class VideoRemoteImpl@Inject constructor(private val vimeoApiService: VimeoApiService)  : VideoRemote {

  override fun getVideos(): List<Video> {
    vimeoApiService.getVideos()
  }
}