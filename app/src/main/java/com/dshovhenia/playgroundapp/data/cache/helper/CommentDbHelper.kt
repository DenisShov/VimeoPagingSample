package com.dshovhenia.playgroundapp.data.cache.helper

import com.dshovhenia.playgroundapp.data.cache.dao.connection.ConnectionDao
import com.dshovhenia.playgroundapp.data.cache.dao.comment.CommentDao
import com.dshovhenia.playgroundapp.data.cache.dao.pictures.PictureSizesDao
import com.dshovhenia.playgroundapp.data.cache.dao.pictures.PicturesDao
import com.dshovhenia.playgroundapp.data.cache.dao.user.UserDao
import com.dshovhenia.playgroundapp.data.cache.dao.user.UserMetadataDao
import com.dshovhenia.playgroundapp.data.cache.db.VimeoDatabase
import com.dshovhenia.playgroundapp.data.cache.model.connection.CachedConnection
import com.dshovhenia.playgroundapp.data.cache.model.comment.CachedComment
import com.dshovhenia.playgroundapp.data.cache.model.pictures.CachedPictureSizes
import com.dshovhenia.playgroundapp.data.cache.model.pictures.CachedPictures
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUser
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUserMetadata
import timber.log.Timber
import javax.inject.Inject

class CommentDbHelper @Inject constructor(vimeoDatabase: VimeoDatabase) {

  val commentDao: CommentDao = vimeoDatabase.commentDao()

  var userDao: UserDao = vimeoDatabase.userDao()
  var userMetadataDao: UserMetadataDao = vimeoDatabase.userMetadataDao()

  var picturesDao: PicturesDao = vimeoDatabase.picturesDao()
  var pictureSizesDao: PictureSizesDao = vimeoDatabase.pictureSizesDao()
  var connectionDao: ConnectionDao = vimeoDatabase.connectionDao()

  fun insertComments(comments: List<CachedComment>) {
    Timber.d("insert comments")
    for (comment in comments) {
      insertComment(comment)
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

    user.pictures?.also {
      it.userId = id
      insertPicturesForUser(it)
    }

    user.userMetadata?.also {
      it.userId = id
      insertUserMetadataForUser(it)
    }
  }

  private fun insertPicturesForUser(pictures: CachedPictures) {
    Timber.d("insert pictures")
    val id = picturesDao.insertPictures(pictures)

    pictures.sizes.forEach {
      it.picturesId = id
    }
    pictures.sizes?.also {
      insertPictureSizesForPictures(it)
    }
  }

  private fun insertPictureSizesForPictures(pictureSizes: List<CachedPictureSizes>) {
    Timber.d("saving pictureSizes")
    pictureSizesDao.insertPictureSizes(pictureSizes)
  }

  private fun insertUserMetadataForUser(userMetadata: CachedUserMetadata) {
    Timber.d("insert userMetadata")
    val id = userMetadataDao.insertUserMetadata(userMetadata)

    userMetadata.followersConnection?.also {
      it.userMetadataId = id
      insertUserConnection(it)
    }

    userMetadata.videosConnection?.also {
      it.userMetadataId = id
      insertUserConnection(it)
    }
  }

  private fun insertUserConnection(
    connection: CachedConnection
  ) {
    Timber.d("insert connection")
    connectionDao.insertConnection(connection)
  }

}