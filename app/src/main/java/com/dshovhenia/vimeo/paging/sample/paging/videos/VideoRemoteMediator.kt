package com.dshovhenia.vimeo.paging.sample.paging.videos

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.dshovhenia.vimeo.paging.sample.data.cache.helper.VideoDbHelper
import com.dshovhenia.vimeo.paging.sample.data.cache.mapper.video.VideoMapper
import com.dshovhenia.vimeo.paging.sample.data.cache.model.video.RelationsVideo
import com.dshovhenia.vimeo.paging.sample.data.model.Collection
import com.dshovhenia.vimeo.paging.sample.data.model.video.Video
import com.dshovhenia.vimeo.paging.sample.data.remote.service.VimeoApiService
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class VideoRemoteMediator(
    private val initialUri: String,
    private val searchQuery: String?,
    private val videoDbHelper: VideoDbHelper,
    private val videoMapper: VideoMapper,
    private val service: VimeoApiService
) : RemoteMediator<Int, RelationsVideo>() {

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, RelationsVideo>
  ): MediatorResult {
    return try {
      val loadKey = when (loadType) {
        LoadType.REFRESH -> null
        LoadType.PREPEND -> return MediatorResult.Success(
          endOfPaginationReached = true
        )
        LoadType.APPEND -> {
          val remoteKey =
            state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()?.video?.nextPage

          // You must explicitly check if the page key is null when
          // appending, since null is only valid for initial load.
          // If you receive null for APPEND, that means you have
          // reached the end of pagination and there are no more
          // items to load.
          if (remoteKey == null) {
            return MediatorResult.Success(endOfPaginationReached = true)
          }

          remoteKey
        }
      }

      val response = if (loadKey == null) {
        service.getVideos(initialUri, searchQuery, 1, PER_PAGE_COUNT)
      } else {
        service.getVideos(loadKey, null, null, null)
      }

      if (loadType == LoadType.REFRESH) {
        videoDbHelper.clear()
      }

      if (response.isSuccessful) {
        val collection = response.body()
        if (collection != null && collection.data.isNotEmpty()) {
          // Store loaded data, and next key in transaction, so that
          // they're always consistent.
          val videos = addLinkToNextPage(collection)
          videoDbHelper.insertVideos(videos.map { videoMapper.mapTo(it) })
        } else {
          Timber.i("No data. Response: %s", response)
        }
      }

      MediatorResult.Success(endOfPaginationReached = response.body()?.data.isNullOrEmpty())
    } catch (e: IOException) {
      e.printStackTrace()
      MediatorResult.Error(e)
    } catch (e: HttpException) {
      e.printStackTrace()
      MediatorResult.Error(e)
    }
  }

  private fun addLinkToNextPage(collection: Collection<Video>) =
    collection.data.map {
      it.nextPage = collection.paging!!.next ?: ""
      it
    }

  companion object {
    private const val PER_PAGE_COUNT = 20
  }

}
