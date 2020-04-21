package com.dshovhenia.playgroundapp.paging.base

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import com.dshovhenia.playgroundapp.data.model.Collection
import com.dshovhenia.playgroundapp.paging.ResultState

abstract class BaseDataSource<T : Parcelable>(private val scope: CoroutineScope) :
  PageKeyedDataSource<String, T>() {

  private var retryAction: Runnable? = null
  private var currentJob: Job? = null

  val stateLiveData = MutableLiveData<ResultState>()

  protected abstract suspend fun getLoadInitialObservable(params: LoadInitialParams<String>): Response<Collection<T>>
  protected abstract suspend fun getLoadAfterObservable(params: LoadParams<String>): Response<Collection<T>>

  override fun loadInitial(
    params: LoadInitialParams<String>, callback: LoadInitialCallback<String, T>
  ) {
    currentJob = scope.launch {
      try {
        updateState(ResultState.LOADING_INITIAL)

        val response = getLoadInitialObservable(params)
        if (response.isSuccessful) {
          val collection = response.body()!!
          if (collection.data.isEmpty()) {
            updateState(ResultState.NO_DATA)
          } else {
            updateState(ResultState.SUCCESS)
          }
          callback.onResult(collection.data, null, collection.paging!!.next)
        }
      } catch (exception: Exception) {
        updateState(ResultState.ERROR)
        setRetry(Runnable { loadInitial(params, callback) })
      }
    }
  }

  override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, T>) {

    currentJob = scope.launch {
      try {
        updateState(ResultState.LOADING_MORE)

        val response = getLoadAfterObservable(params)
        if (response.isSuccessful) {
          val collection = response.body()!!
          updateState(ResultState.SUCCESS)
          callback.onResult(collection.data, collection.paging!!.next)
        }
      } catch (exception: Exception) {
        updateState(ResultState.ERROR)
        setRetry(Runnable { loadAfter(params, callback) })
      }
    }
  }

  override fun loadBefore(params: LoadParams<String?>, callback: LoadCallback<String?, T>) {
    // no need to load the data before.
  }

  override fun invalidate() {
    super.invalidate()
    currentJob?.cancel()
  }

  private fun updateState(resultState: ResultState) {
    stateLiveData.postValue(resultState)
  }

  fun retry() {
    retryAction?.run()
  }

  private fun setRetry(action: Runnable) {
    retryAction = action
  }

}