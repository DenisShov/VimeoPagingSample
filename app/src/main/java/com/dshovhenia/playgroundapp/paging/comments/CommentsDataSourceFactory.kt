package com.dshovhenia.playgroundapp.paging.comments

import kotlinx.coroutines.CoroutineScope
import com.dshovhenia.playgroundapp.data.DataManager
import com.dshovhenia.playgroundapp.data.model.VimeoComment
import com.dshovhenia.playgroundapp.paging.base.BaseDataSource
import com.dshovhenia.playgroundapp.paging.base.BaseDataSourceFactory

class CommentsDataSourceFactory(
  private val mDataManager: DataManager, private val scope: CoroutineScope
) : BaseDataSourceFactory<VimeoComment>() {
  private var mInitialUri: String? = null
  override fun generateDataSource(): BaseDataSource<VimeoComment> {
    return CommentsDataSource(mDataManager, scope, mInitialUri)
  }

  fun setInitialUri(uri: String?) {
    mInitialUri = uri
  }

}