package com.dshovhenia.playgroundapp.injection.component

import com.dshovhenia.playgroundapp.injection.module.ApplicationModule
import com.dshovhenia.playgroundapp.injection.module.CacheModule
import com.dshovhenia.playgroundapp.injection.module.NetworkModule
import com.dshovhenia.playgroundapp.injection.module.viewmodel.ViewModelModule
import com.dshovhenia.playgroundapp.ui.details.comments.CommentsFragment
import com.dshovhenia.playgroundapp.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, CacheModule::class, ViewModelModule::class])
interface ApplicationComponent {

  // Fragments
  fun inject(fragment: HomeFragment)

  fun inject(fragment: CommentsFragment)

}
