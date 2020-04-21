package com.dshovhenia.playgroundapp.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dshovhenia.playgroundapp.data.cache.dao.comment.CommentDao
import com.dshovhenia.playgroundapp.data.cache.dao.connection.ConnectionDao
import com.dshovhenia.playgroundapp.data.cache.dao.pictures.PictureSizesDao
import com.dshovhenia.playgroundapp.data.cache.dao.pictures.PicturesDao
import com.dshovhenia.playgroundapp.data.cache.dao.user.UserDao
import com.dshovhenia.playgroundapp.data.cache.dao.user.UserMetadataDao
import com.dshovhenia.playgroundapp.data.cache.dao.video.VideoDao
import com.dshovhenia.playgroundapp.data.cache.dao.video.VideoMetadataDao
import com.dshovhenia.playgroundapp.data.cache.dao.video.VideoStatsDao
import com.dshovhenia.playgroundapp.data.cache.model.comment.CachedComment
import com.dshovhenia.playgroundapp.data.cache.model.connection.CachedConnection
import com.dshovhenia.playgroundapp.data.cache.model.pictures.CachedPictureSizes
import com.dshovhenia.playgroundapp.data.cache.model.pictures.CachedPictures
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUser
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUserMetadata
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideo
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideoMetadata
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideoStats
import javax.inject.Inject

@Database(
  entities = [CachedComment::class, CachedVideo::class, CachedVideoMetadata::class,
    CachedVideoStats::class, CachedPictures::class, CachedPictureSizes::class,
    CachedUser::class, CachedUserMetadata::class, CachedConnection::class
  ], version = 1
)
abstract class VimeoDatabase @Inject constructor() : RoomDatabase() {

  abstract fun commentDao(): CommentDao

  abstract fun videoDao(): VideoDao
  abstract fun videoMetadataDao(): VideoMetadataDao
  abstract fun videoStatsDao(): VideoStatsDao

  abstract fun picturesDao(): PicturesDao
  abstract fun pictureSizesDao(): PictureSizesDao

  abstract fun userDao(): UserDao
  abstract fun userMetadataDao(): UserMetadataDao

  abstract fun connectionDao(): ConnectionDao

}