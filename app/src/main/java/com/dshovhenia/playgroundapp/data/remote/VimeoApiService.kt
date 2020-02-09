package com.dshovhenia.playgroundapp.data.remote

import retrofit2.Response
import retrofit2.http.*
import com.dshovhenia.playgroundapp.data.local.AccessToken
import com.dshovhenia.playgroundapp.data.model.VimeoCollection
import com.dshovhenia.playgroundapp.data.model.VimeoComment
import com.dshovhenia.playgroundapp.data.model.VimeoVideo

interface VimeoApiService {

  @POST("oauth/authorize/client?grant_type=client_credentials")
  suspend fun getUnauthenticatedToken(@Header("Authorization") basic_auth: String?): AccessToken

  @GET
  suspend fun getVideoList(
    @Url url: String?, @Query("query") query: String?, @Query("page") page: Int?, @Query("per_page") per_page: Int?
  ): Response<VimeoCollection<VimeoVideo>>

  @GET
  suspend fun getComments(
    @Url url: String?, @Query("direction") direction: String?, @Query("page") page: Int?, @Query("per_page") per_page: Int?
  ): Response<VimeoCollection<VimeoComment>>
}