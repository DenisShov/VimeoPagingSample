package com.dshovhenia.playgroundapp.data.cache.mapper.video

import com.dshovhenia.playgroundapp.data.cache.mapper.Mapper
import com.dshovhenia.playgroundapp.data.cache.mapper.pictures.CachedPicturesMapper
import com.dshovhenia.playgroundapp.data.cache.mapper.user.UserMapper
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideo
import com.dshovhenia.playgroundapp.data.model.video.Video
import javax.inject.Inject

class VideoMapper @Inject constructor(
  private val userMapper: UserMapper,
  private val picturesMapper: CachedPicturesMapper,
  private val videoMetadataMapper: VideoMetadataMapper,
  private val statsMapper: VideoStatsMapper
) : Mapper<CachedVideo, Video> {

  override fun mapFrom(type: CachedVideo?): Video? {
    return type?.let {
      Video(
        it.uri, it.name, it.description, it.duration, it.createdTime,
        userMapper.mapFrom(it.user),
        picturesMapper.mapFrom(it.pictures),
        videoMetadataMapper.mapFrom(it.metadata),
        statsMapper.mapFrom(it.stats)
      )
    }
  }

  override fun mapTo(type: Video?): CachedVideo? {
    return type?.let {
      CachedVideo(
        it.uri, it.name, it.description, it.duration, it.createdTime,
        userMapper.mapTo(it.user),
        picturesMapper.mapTo(it.pictures),
        videoMetadataMapper.mapTo(it.metadata),
        statsMapper.mapTo(it.stats)
      )
    }
  }
}