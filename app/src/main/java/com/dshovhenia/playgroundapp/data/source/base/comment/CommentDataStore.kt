package com.dshovhenia.playgroundapp.data.source.base.comment

import com.dshovhenia.playgroundapp.data.model.comment.Comment

interface CommentDataStore {

  fun clearComments()

  fun saveComments(comments: List<Comment>)

  fun getComments(): List<Comment>

  fun isCached(): Boolean

  fun isExpired(): Boolean

}
