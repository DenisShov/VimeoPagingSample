package com.dshovhenia.playgroundapp.data.cache.mapper.comment

import com.dshovhenia.playgroundapp.data.cache.mapper.Mapper
import com.dshovhenia.playgroundapp.data.cache.mapper.user.UserMapper
import com.dshovhenia.playgroundapp.data.cache.model.comment.CachedComment
import com.dshovhenia.playgroundapp.data.model.comment.Comment
import javax.inject.Inject

class CommentMapper @Inject constructor(private val userMapper: UserMapper) :
  Mapper<CachedComment, Comment> {

  /**
   * Map a [CachedComment] instance to a [Comment] instance
   */
  override fun mapFrom(type: CachedComment?): Comment? {
    return type?.let {
      Comment(
        type.uri,
        type.text,
        type.created_on,
        userMapper.mapFrom(type.user)
      )
    }
  }

  /**
   * Map a [Comment] instance to a [CachedComment] instance
   */
  override fun mapTo(type: Comment?): CachedComment? {
    return type?.let {
      CachedComment(
        type.uri,
        type.text,
        type.created_on,
        userMapper.mapTo(type.user)
      )
    }
  }

}