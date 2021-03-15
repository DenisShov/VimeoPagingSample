package com.dshovhenia.playgroundapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.dshovhenia.playgroundapp.data.cache.db.VimeoDatabase
import com.dshovhenia.playgroundapp.data.cache.helper.VideoDbHelper
import com.dshovhenia.playgroundapp.data.cache.mapper.relations_mapper.RelationsVideoMapper
import com.dshovhenia.playgroundapp.data.cache.mapper.video.VideoMapper
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideo
import com.dshovhenia.playgroundapp.data.remote.service.VimeoApiService
import com.dshovhenia.playgroundapp.paging.videos.VideoRemoteMediator
import com.dshovhenia.playgroundapp.ui.home.HomeViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoRepository @Inject constructor(
  private val vimeoApiService: VimeoApiService,
  private val vimeoDatabase: VimeoDatabase,
  private val videoDbHelper: VideoDbHelper,
  private val videoMapper: VideoMapper,
  private val relationsVideoMapper: RelationsVideoMapper
) {

  fun getVideos(
    initialUri: String,
    searchQuery: String?
  ) = Pager(
    config = PagingConfig(pageSize = HomeViewModel.NETWORK_PAGE_SIZE),
    remoteMediator = VideoRemoteMediator(
      initialUri,
      searchQuery,
      videoDbHelper,
      videoMapper,
      vimeoApiService
    ),
    pagingSourceFactory = { vimeoDatabase.videoDao().getVideos() }
  ).liveData

  fun getVideoById(videoId: Long): CachedVideo {
    val relationsVideo = vimeoDatabase.videoDao().getVideoById(videoId)
    return relationsVideoMapper.mapFrom(relationsVideo)
  }

  fun clearVideos() = videoDbHelper.clear()
}