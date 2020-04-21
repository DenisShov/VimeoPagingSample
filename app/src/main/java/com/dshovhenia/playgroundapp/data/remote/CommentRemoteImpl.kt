package com.dshovhenia.playgroundapp.data.remote

import com.dshovhenia.playgroundapp.data.model.comment.Comment
import com.dshovhenia.playgroundapp.data.remote.service.VimeoApiService
import com.dshovhenia.playgroundapp.data.source.base.comment.CommentRemote
import javax.inject.Inject

class CommentRemoteImpl @Inject constructor(private val vimeoApiService: VimeoApiService) :
  CommentRemote {

  override fun getComments(
    url: String?, direction: String?, page: Int?, per_page: Int?
  ): List<Comment> {
    vimeoApiService.getComments(url, direction, page, per_page)
  }
}