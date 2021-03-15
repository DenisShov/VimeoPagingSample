package com.dshovhenia.playgroundapp.data.cache.helper

import com.dshovhenia.playgroundapp.data.cache.db.VimeoDatabase
import com.dshovhenia.playgroundapp.data.cache.model.comment.CachedComment
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUser
import timber.log.Timber
import javax.inject.Inject

class CommentDbHelper @Inject constructor(val vimeoDatabase: VimeoDatabase) {

  val commentDao = vimeoDatabase.commentDao()
  var userDao = vimeoDatabase.userDao()
  var pictureSizesDao = vimeoDatabase.pictureSizesDao()

  fun clear() {
    commentDao.clearComments()
  }

  fun insertComments(comments: List<CachedComment>) {
    Timber.d("insert comments")

    vimeoDatabase.runInTransaction {
      for (comment in comments) {
        insertComment(comment)
      }
    }
  }

  fun insertComment(comment: CachedComment) {
    Timber.d("insert comment")
    val id = commentDao.insertComment(comment)

    comment.user?.also {
      it.commentId = id
      insertUser(it)
    }
  }

  private fun insertUser(user: CachedUser) {
    Timber.d("insert user")
    val id = userDao.insertUser(user)

    user.pictureSizes.forEach {
      it.userId = id
    }
    user.pictureSizes.also {
      pictureSizesDao.insertPictureSizes(it)
    }
  }
}
