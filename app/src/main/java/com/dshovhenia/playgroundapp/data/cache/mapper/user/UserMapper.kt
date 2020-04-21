package com.dshovhenia.playgroundapp.data.cache.mapper.user

import com.dshovhenia.playgroundapp.data.cache.mapper.Mapper
import com.dshovhenia.playgroundapp.data.cache.mapper.pictures.CachedPicturesMapper
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUser
import com.dshovhenia.playgroundapp.data.model.user.User
import javax.inject.Inject

class UserMapper @Inject constructor(
  private val picturesMapper: CachedPicturesMapper,
  private val userMetadataMapper: UserMetadataMapper
) : Mapper<CachedUser, User> {

  override fun mapFrom(type: CachedUser?): User? {
    return type?.let {
      User(
        it.name,
        picturesMapper.mapFrom(it.pictures),
        userMetadataMapper.mapFrom(it.userMetadata)
      )
    }
  }

  override fun mapTo(type: User?): CachedUser? {
    return type?.let {
      CachedUser(
        it.name,
        picturesMapper.mapTo(it.pictures),
        userMetadataMapper.mapTo(it.userMetadata)
      )
    }
  }
}