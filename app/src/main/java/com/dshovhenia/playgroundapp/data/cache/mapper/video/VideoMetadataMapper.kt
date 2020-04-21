package com.dshovhenia.playgroundapp.data.cache.mapper.video

import com.dshovhenia.playgroundapp.data.cache.mapper.Mapper
import com.dshovhenia.playgroundapp.data.cache.mapper.connection.ConnectionMapper
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideoMetadata
import com.dshovhenia.playgroundapp.data.model.video.VideoMetadata
import javax.inject.Inject

class VideoMetadataMapper @Inject constructor(private val connectionMapper: ConnectionMapper) :
  Mapper<CachedVideoMetadata, VideoMetadata> {

  override fun mapFrom(type: CachedVideoMetadata?): VideoMetadata? {
    return type?.let {
      VideoMetadata(
        connectionMapper.mapFrom(it.commentsConnection),
        connectionMapper.mapFrom(it.likesConnection)
      )
    }
  }

  override fun mapTo(type: VideoMetadata?): CachedVideoMetadata? {
    return type?.let {
      CachedVideoMetadata(
        connectionMapper.mapTo(it.commentsConnection),
        connectionMapper.mapTo(it.likesConnection)
      )
    }
  }
}