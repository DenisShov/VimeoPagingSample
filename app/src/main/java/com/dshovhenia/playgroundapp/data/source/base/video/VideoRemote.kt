package com.dshovhenia.playgroundapp.data.source.base.video

import com.dshovhenia.playgroundapp.data.model.video.Video

interface VideoRemote {

  /**
   * Retrieve a list of Videos, from the remote
   */
  fun getVideos(url: String?, query: String?, page: Int?, per_page: Int?): List<Video>

}