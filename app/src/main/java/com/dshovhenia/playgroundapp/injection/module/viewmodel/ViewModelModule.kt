package com.dshovhenia.playgroundapp.injection.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.dshovhenia.playgroundapp.ui.details.comments.CommentsViewModel
import com.dshovhenia.playgroundapp.ui.home.HomeViewModel

@Module
abstract class ViewModelModule {

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(HomeViewModel::class)
  abstract fun bindsHomeViewModel(viewModel: HomeViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(CommentsViewModel::class)
  abstract fun bindsCommentsViewModel(viewModel: CommentsViewModel): ViewModel

}