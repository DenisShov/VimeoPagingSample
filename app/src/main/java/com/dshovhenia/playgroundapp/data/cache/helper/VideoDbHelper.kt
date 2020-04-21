package com.dshovhenia.playgroundapp.data.cache.helper

import com.dshovhenia.playgroundapp.data.cache.dao.connection.ConnectionDao
import com.dshovhenia.playgroundapp.data.cache.dao.pictures.PictureSizesDao
import com.dshovhenia.playgroundapp.data.cache.dao.pictures.PicturesDao
import com.dshovhenia.playgroundapp.data.cache.dao.user.UserDao
import com.dshovhenia.playgroundapp.data.cache.dao.user.UserMetadataDao
import com.dshovhenia.playgroundapp.data.cache.dao.video.VideoDao
import com.dshovhenia.playgroundapp.data.cache.dao.video.VideoMetadataDao
import com.dshovhenia.playgroundapp.data.cache.dao.video.VideoStatsDao
import com.dshovhenia.playgroundapp.data.cache.db.VimeoDatabase
import com.dshovhenia.playgroundapp.data.cache.model.connection.CachedConnection
import com.dshovhenia.playgroundapp.data.cache.model.pictures.CachedPictureSizes
import com.dshovhenia.playgroundapp.data.cache.model.pictures.CachedPictures
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUser
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUserMetadata
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideo
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideoMetadata
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideoStats
import timber.log.Timber
import javax.inject.Inject

class VideoDbHelper @Inject constructor(vimeoDatabase: VimeoDatabase) {

  val videoDao: VideoDao = vimeoDatabase.videoDao()
  val videoMetadataDao: VideoMetadataDao = vimeoDatabase.videoMetadataDao()
  val videoStatsDao: VideoStatsDao = vimeoDatabase.videoStatsDao()

  var userDao: UserDao = vimeoDatabase.userDao()
  var userMetadataDao: UserMetadataDao = vimeoDatabase.userMetadataDao()

  var picturesDao: PicturesDao = vimeoDatabase.picturesDao()
  var pictureSizesDao: PictureSizesDao = vimeoDatabase.pictureSizesDao()

  var connectionDao: ConnectionDao = vimeoDatabase.connectionDao()

  fun insertVideos(videos: List<CachedVideo>) {
    Timber.d("saving videos")
    for (video in videos) {
      insertVideo(video)
    }
  }

  fun insertVideo(video: CachedVideo) {
    Timber.d("saving video")
    val id = videoDao.insertVideo(video)

    video.user?.also {
      it.videoId = id
      insertUser(it)
    }

    video.pictures?.also {
      it.videoId = id
      insertPictures(it)
    }

    video.metadata?.also {
      it.videoId = id
      insertVideoMetadata(it)
    }

    video.stats?.also {
      it.videoId = id
      insertVideoStats(it)
    }
  }

  private fun insertVideoStats(videoStats: CachedVideoStats) {
    Timber.d("saving videoStats")
    videoStatsDao.insertVideoStats(videoStats)
  }

  private fun insertVideoMetadata(videoMetadata: CachedVideoMetadata) {
    Timber.d("saving videoMetadata")
    val id = videoMetadataDao.insertVideoMetadata(videoMetadata)

    videoMetadata.commentsConnection?.also {
      it.videoMetadataId = id
      insertVideoConnection(it)
    }

    videoMetadata.likesConnection?.also {
      it.videoMetadataId = id
      insertVideoConnection(it)
    }
  }

  private fun insertUser(user: CachedUser) {
    Timber.d("saving user")
    val id = userDao.insertUser(user)

    user.pictures?.also {
      it.userId = id
      insertPictures(it)
    }

    user.userMetadata?.also {
      it.userId = id
      insertUserMetadataForUser(it)
    }
  }

  private fun insertPictures(pictures: CachedPictures) {
    Timber.d("saving pictures")
    val id = picturesDao.insertPictures(pictures)

    pictures.sizes.forEach {
      it.picturesId = id
    }
    pictures.sizes.also {
      insertPictureSizesForPictures(it)
    }
  }

  private fun insertUserMetadataForUser(userMetadata: CachedUserMetadata) {
    Timber.d("saving userMetadata")
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

  private fun insertPictureSizesForPictures(pictureSizes: List<CachedPictureSizes>) {
    Timber.d("saving pictureSizes")
    pictureSizesDao.insertPictureSizes(pictureSizes)
  }

  private fun insertUserConnection(
    connection: CachedConnection
  ) {
    Timber.d("saving user connection")
    connectionDao.insertConnection(connection)
  }

  private fun insertVideoConnection(connection: CachedConnection) {
    Timber.d("saving video connection")
    connectionDao.insertConnection(connection)
  }

}