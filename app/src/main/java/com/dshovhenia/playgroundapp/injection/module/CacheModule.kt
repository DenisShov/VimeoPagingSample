package com.dshovhenia.playgroundapp.injection.module

import android.app.Application
import androidx.room.Room
import com.dshovhenia.playgroundapp.data.cache.CommentCacheImpl
import com.dshovhenia.playgroundapp.data.cache.VideoCacheImpl
import com.dshovhenia.playgroundapp.data.cache.db.VimeoDatabase
import com.dshovhenia.playgroundapp.data.source.base.comment.CommentCache
import com.dshovhenia.playgroundapp.data.source.base.video.VideoCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

  /**
   * This companion object annotated as a module is necessary in order to provide dependencies
   * statically in case the wrapping module is an abstract class (to use binding)
   */
  @Module
  companion object {

    @Provides
    @JvmStatic
    fun provideVimeoDatabase(application: Application): VimeoDatabase {
      return Room.databaseBuilder(
        application.applicationContext,
        VimeoDatabase::class.java, "vimeo_database.db"
      ).build()
    }
  }

  @Binds
  abstract fun bindCommentCache(commentCacheImpl: CommentCacheImpl): CommentCache

  @Binds
  abstract fun bindVideoCache(videoCacheImpl: VideoCacheImpl): VideoCache
}