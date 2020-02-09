package com.dshovhenia.playgroundapp.injection.component

import dagger.Component
import com.dshovhenia.playgroundapp.injection.module.ApplicationModule
import com.dshovhenia.playgroundapp.injection.module.NetworkModule
import com.dshovhenia.playgroundapp.injection.module.viewmodel.ViewModelModule
import com.dshovhenia.playgroundapp.ui.details.comments.CommentsFragment
import com.dshovhenia.playgroundapp.ui.home.HomeFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class])
interface ApplicationComponent {

  // Fragments
  fun inject(fragment: HomeFragment)

  fun inject(fragment: CommentsFragment)

}
