package com.dshovhenia.playgroundapp.data.cache.mapper.user

import com.dshovhenia.playgroundapp.data.cache.mapper.Mapper
import com.dshovhenia.playgroundapp.data.cache.mapper.connection.ConnectionMapper
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUserMetadata
import com.dshovhenia.playgroundapp.data.model.user.UserMetadata
import javax.inject.Inject

class UserMetadataMapper @Inject constructor(private val connectionMapper: ConnectionMapper) :
  Mapper<CachedUserMetadata, UserMetadata> {

  override fun mapFrom(type: CachedUserMetadata?): UserMetadata? {
    return type?.let {
      UserMetadata(
        connectionMapper.mapFrom(it.followersConnection),
        connectionMapper.mapFrom(it.videosConnection)
      )
    }
  }

  override fun mapTo(type: UserMetadata?): CachedUserMetadata? {
    return type?.let {
      CachedUserMetadata(
        connectionMapper.mapTo(it.followersConnection),
        connectionMapper.mapTo(it.videosConnection)
      )
    }
  }
}