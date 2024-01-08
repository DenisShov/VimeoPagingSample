package com.dshovhenia.vimeo.paging.sample.data.cache.mapper.comment

import com.dshovhenia.vimeo.paging.sample.data.cache.mapper.Mapper
import com.dshovhenia.vimeo.paging.sample.data.cache.mapper.user.UserMapper
import com.dshovhenia.vimeo.paging.sample.data.cache.model.comment.CachedComment
import com.dshovhenia.vimeo.paging.sample.data.model.comment.Comment
import javax.inject.Inject

class CommentMapper @Inject constructor(private val userMapper: UserMapper) :
    Mapper<CachedComment, Comment> {

  override fun mapFrom(type: CachedComment) =
    Comment(
      type.uri,
      type.text,
      type.created_on,
      type.nextPage,
      userMapper.mapFrom(type.user!!)
    )

  override fun mapTo(type: Comment) =
    CachedComment(
      type.uri,
      type.text,
      type.createdOn,
      type.nextPage,
      userMapper.mapTo(type.user!!)
    )
}
