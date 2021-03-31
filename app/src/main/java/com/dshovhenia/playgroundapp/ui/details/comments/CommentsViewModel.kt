package com.dshovhenia.playgroundapp.ui.details.comments

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dshovhenia.playgroundapp.data.cache.model.comment.RelationsComment
import com.dshovhenia.playgroundapp.data.repository.CommentRepository

class CommentsViewModel @ViewModelInject constructor(
  private val commentRepository: CommentRepository
) : ViewModel() {

  companion object {
    const val NETWORK_PAGE_SIZE = 50
  }

  private val queryLiveData = MutableLiveData<String>()
  val commentResult: LiveData<PagingData<RelationsComment>> =
    queryLiveData.switchMap { initialUri ->
      liveData {
        val repos = getResultStream(initialUri)
        emitSource(repos)
      }
    }

  private fun getResultStream(initialUri: String) =
    commentRepository.getComments(initialUri).cachedIn(viewModelScope)

  fun onInitialListRequested(url: String) {
    commentRepository.clearComments()
    queryLiveData.postValue(url)
  }
}
