package com.dshovhenia.vimeo.paging.sample.paging.comments

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.dshovhenia.vimeo.paging.sample.data.cache.helper.CommentDbHelper
import com.dshovhenia.vimeo.paging.sample.data.cache.mapper.comment.CommentMapper
import com.dshovhenia.vimeo.paging.sample.data.cache.model.comment.RelationsComment
import com.dshovhenia.vimeo.paging.sample.data.model.Collection
import com.dshovhenia.vimeo.paging.sample.data.model.comment.Comment
import com.dshovhenia.vimeo.paging.sample.data.remote.service.VimeoApiService
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

@OptIn(androidx.paging.ExperimentalPagingApi::class)
class CommentRemoteMediator(
    private val initialUri: String,
    private val commentDbHelper: CommentDbHelper,
    private val commentMapper: CommentMapper,
    private val service: VimeoApiService
) : RemoteMediator<Int, RelationsComment>() {

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, RelationsComment>
  ): MediatorResult {
    return try {
      val loadKey = when (loadType) {
        LoadType.REFRESH -> null
        LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
        LoadType.APPEND -> {
          val remoteKey =
            state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()?.comment?.nextPage

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
        service.getComments(initialUri, null, 1, PER_PAGE_COUNT)
      } else {
        service.getComments(loadKey, null, null, null)
      }

      if (loadType == LoadType.REFRESH) {
        commentDbHelper.clear()
      }

      if (response.isSuccessful) {
        val collection = response.body()
        if (collection != null && collection.data.isNotEmpty()) {
          // Store the loaded data and the next key in transaction, so that
          // they're always consistent.
          val videos = addLinkToNextPage(collection)
          commentDbHelper.insertComments(videos.map { commentMapper.mapTo(it) })
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

  private fun addLinkToNextPage(collection: Collection<Comment>) =
    collection.data.map {
      it.nextPage = collection.paging!!.next ?: ""
      it
    }

  companion object {
    private const val PER_PAGE_COUNT = 20
  }

}
