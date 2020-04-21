package com.dshovhenia.playgroundapp.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import com.dshovhenia.playgroundapp.AndroidApplication
import com.dshovhenia.playgroundapp.data.repository.Repository
import com.dshovhenia.playgroundapp.data.remote.service.VimeoApiService
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    fun provideDataManager(vimeoApiService: VimeoApiService): Repository {
        return Repository(
          vimeoApiService
        )
    }

}