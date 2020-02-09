package com.dshovhenia.playgroundapp.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import com.dshovhenia.playgroundapp.AndroidApplication
import com.dshovhenia.playgroundapp.data.DataManager
import com.dshovhenia.playgroundapp.data.remote.VimeoApiService
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    fun provideDataManager(vimeoApiService: VimeoApiService): DataManager {
        return DataManager(vimeoApiService)
    }

}