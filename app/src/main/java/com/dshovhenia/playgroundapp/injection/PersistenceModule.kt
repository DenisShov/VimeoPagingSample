package com.dshovhenia.playgroundapp.injection

import android.app.Application
import androidx.room.Room
import com.dshovhenia.playgroundapp.data.cache.db.VimeoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PersistenceModule {

  @Singleton
  @Provides
  fun provideDb(app: Application): VimeoDatabase {
    return Room
      .databaseBuilder(app, VimeoDatabase::class.java, "vimeo_database.db")
      .allowMainThreadQueries()
      .build()
  }

}
