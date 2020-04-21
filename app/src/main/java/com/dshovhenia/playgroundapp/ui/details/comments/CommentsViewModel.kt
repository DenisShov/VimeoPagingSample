package com.dshovhenia.playgroundapp.ui.details.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dshovhenia.playgroundapp.data.repository.Repository
import com.dshovhenia.playgroundapp.data.model.comment.Comment
import com.dshovhenia.playgroundapp.paging.comments.CommentsDataSourceFactory
import javax.inject.Inject

class CommentsViewModel @Inject constructor(private var mRepository: Repository) : ViewModel() {

  private val dataSourceFactory = CommentsDataSourceFactory(
    mRepository, viewModelScope
  )

  internal val commentListLiveData: LiveData<PagedList<Comment>>
  internal val stateLiveData =
    Transformations.switchMap(dataSourceFactory.collectionDataSourceLiveData) {
      it.stateLiveData
    }

  init {
    val config = PagedList.Config.Builder().setPageSize(10).setInitialLoadSizeHint(20)
      .setEnablePlaceholders(false).build()
    commentListLiveData = LivePagedListBuilder(dataSourceFactory, config).build()
  }

  fun onInitialListRequested(url: String) {
    dataSourceFactory.setInitialUri(url)
    invalidateDataSource()
  }

  fun retry() {
    dataSourceFactory.collectionDataSourceLiveData.value?.retry()
  }

  fun invalidateDataSource() {
    dataSourceFactory.collectionDataSourceLiveData.value?.invalidate()
  }

}
