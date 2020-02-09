package com.dshovhenia.playgroundapp.data

import retrofit2.Response
import com.dshovhenia.playgroundapp.data.model.VimeoCollection
import com.dshovhenia.playgroundapp.data.model.VimeoComment
import com.dshovhenia.playgroundapp.data.model.VimeoVideo
import com.dshovhenia.playgroundapp.data.remote.VimeoApiService
import javax.inject.Inject

class DataManager @Inject constructor(private val mVimeoApiService: VimeoApiService) {

  suspend fun getVideoList(
    url: String?, query: String?, page: Int?, per_page: Int?
  ): Response<VimeoCollection<VimeoVideo>> {
    return mVimeoApiService.getVideoList(url, query, page, per_page)
  }

  suspend fun getCommentList(
    url: String?, direction: String?, page: Int?, per_page: Int?
  ): Response<VimeoCollection<VimeoComment>> {
    return mVimeoApiService.getComments(url, direction, page, per_page)
  }

}