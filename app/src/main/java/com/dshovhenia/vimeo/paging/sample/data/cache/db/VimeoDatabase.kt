package com.dshovhenia.vimeo.paging.sample.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dshovhenia.vimeo.paging.sample.data.cache.dao.comment.CommentDao
import com.dshovhenia.vimeo.paging.sample.data.cache.dao.pictures.PictureSizesDao
import com.dshovhenia.vimeo.paging.sample.data.cache.dao.user.UserDao
import com.dshovhenia.vimeo.paging.sample.data.cache.dao.video.VideoDao
import com.dshovhenia.vimeo.paging.sample.data.cache.model.comment.CachedComment
import com.dshovhenia.vimeo.paging.sample.data.cache.model.pictures.CachedPictureSizes
import com.dshovhenia.vimeo.paging.sample.data.cache.model.user.CachedUser
import com.dshovhenia.vimeo.paging.sample.data.cache.model.video.CachedVideo

@Database(
  entities = [CachedComment::class, CachedVideo::class,
    CachedPictureSizes::class, CachedUser::class
  ], version = 1, exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class VimeoDatabase : RoomDatabase() {
  abstract fun commentDao(): CommentDao
  abstract fun videoDao(): VideoDao
  abstract fun userDao(): UserDao
  abstract fun pictureSizesDao(): PictureSizesDao
}
