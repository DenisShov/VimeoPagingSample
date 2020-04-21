package com.dshovhenia.playgroundapp.data.remote.service

import com.dshovhenia.playgroundapp.data.cache.preferences.model.AccessToken
import com.dshovhenia.playgroundapp.data.model.Collection
import com.dshovhenia.playgroundapp.data.model.comment.Comment
import com.dshovhenia.playgroundapp.data.model.video.Video
import retrofit2.Response
import retrofit2.http.*

interface VimeoApiService {

  @POST("oauth/authorize/client?grant_type=client_credentials")
  suspend fun getUnauthenticatedToken(@Header("Authorization") basic_auth: String?): AccessToken

  @GET
  suspend fun getVideos(
    @Url url: String?, @Query("query") query: String?, @Query("page") page: Int?, @Query("per_page") per_page: Int?
  ): Response<Collection<Video>>

  @GET
  suspend fun getComments(
    @Url url: String?, @Query("direction") direction: String?, @Query("page") page: Int?, @Query("per_page") per_page: Int?
  ): Response<Collection<Comment>>
}