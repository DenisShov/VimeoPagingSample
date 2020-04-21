package com.dshovhenia.playgroundapp.data.repository

import com.dshovhenia.playgroundapp.data.model.Collection
import com.dshovhenia.playgroundapp.data.model.comment.Comment
import com.dshovhenia.playgroundapp.data.model.video.Video
import com.dshovhenia.playgroundapp.data.remote.service.VimeoApiService
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val mVimeoApiService: VimeoApiService) {

  suspend fun getVideoList(
    url: String?, query: String?, page: Int?, per_page: Int?
  ): Response<Collection<Video>> {
    return mVimeoApiService.getVideos(url, query, page, per_page)
  }

  suspend fun getCommentList(
    url: String?, direction: String?, page: Int?, per_page: Int?
  ): Response<Collection<Comment>> {
    return mVimeoApiService.getComments(url, direction, page, per_page)
  }

}