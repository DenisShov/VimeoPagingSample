package com.dshovhenia.playgroundapp.data.repository.base

import com.dshovhenia.playgroundapp.data.model.comment.Comment

interface CommentRepository {

  fun clearComments()

  fun saveComments(comments: List<Comment>)

  fun getComments(url: String?, direction: String?, page: Int?, per_page: Int?): List<Comment>

}