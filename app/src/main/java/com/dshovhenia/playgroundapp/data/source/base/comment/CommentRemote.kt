package com.dshovhenia.playgroundapp.data.source.base.comment

import com.dshovhenia.playgroundapp.data.model.comment.Comment

interface CommentRemote {

  /**
   * Retrieve a list of Comments, from the remote
   */
  fun getComments(url: String?, direction: String?, page: Int?, per_page: Int?): List<Comment>

}