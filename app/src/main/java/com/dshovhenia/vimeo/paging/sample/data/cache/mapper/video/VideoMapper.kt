package com.dshovhenia.vimeo.paging.sample.data.cache.mapper.video

import com.dshovhenia.vimeo.paging.sample.data.cache.mapper.Mapper
import com.dshovhenia.vimeo.paging.sample.data.cache.mapper.pictures.PictureSizesMapper
import com.dshovhenia.vimeo.paging.sample.data.cache.mapper.user.UserMapper
import com.dshovhenia.vimeo.paging.sample.data.cache.model.video.CachedVideo
import com.dshovhenia.vimeo.paging.sample.data.model.Connection
import com.dshovhenia.vimeo.paging.sample.data.model.pictures.Pictures
import com.dshovhenia.vimeo.paging.sample.data.model.video.Video
import com.dshovhenia.vimeo.paging.sample.data.model.video.VideoMetadata
import com.dshovhenia.vimeo.paging.sample.data.model.video.VideoStats
import javax.inject.Inject

class VideoMapper @Inject constructor(
    private val userMapper: UserMapper,
    private val pictureSizesMapper: PictureSizesMapper
) : Mapper<CachedVideo, Video> {

  override fun mapFrom(type: CachedVideo): Video {
    val pictures = Pictures()
    pictures.sizes = type.pictureSizes.map { pictureSizesMapper.mapFrom(it) }

    val videoMetadata = VideoMetadata()
    val commentsConnection = Connection()
    commentsConnection.uri = type.commentsUri
    commentsConnection.total = type.commentsTotal
    val likesConnection = Connection()
    likesConnection.uri = type.likesUri
    likesConnection.total = type.likesTotal
    videoMetadata.commentsConnection = commentsConnection
    videoMetadata.likesConnection = likesConnection

    val videoStats = VideoStats()
    videoStats.plays = type.videoPlays

    return Video(
      type.uri, type.name, type.description, type.duration, type.createdTime, type.nextPage,
      userMapper.mapFrom(type.user!!),
      pictures,
      videoMetadata,
      videoStats
    )
  }

  override fun mapTo(type: Video) =
    CachedVideo(type.uri, type.name, type.description ?: "", type.duration, type.createdTime, type.nextPage,

      type.metadata?.commentsConnection?.uri ?: "",
      type.metadata?.commentsConnection?.total ?: 0,
      type.metadata?.likesConnection?.uri ?: "",
      type.metadata?.likesConnection?.total ?: 0,
      type.stats?.plays ?: 0,

      userMapper.mapTo(type.user!!),
      type.pictures?.sizes?.map { pictureSizesMapper.mapTo(it) } ?: ArrayList()
    )
}
