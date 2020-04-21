package com.dshovhenia.playgroundapp.data.cache.mapper.video

import com.dshovhenia.playgroundapp.data.cache.mapper.Mapper
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideoStats
import com.dshovhenia.playgroundapp.data.model.video.VideoStats
import javax.inject.Inject

class VideoStatsMapper @Inject constructor() : Mapper<CachedVideoStats, VideoStats> {

  override fun mapFrom(type: CachedVideoStats?): VideoStats? {
    return type?.let { VideoStats(it.plays) }
  }

  override fun mapTo(type: VideoStats?): CachedVideoStats? {
    return type?.let { CachedVideoStats(it.plays) }
  }
}