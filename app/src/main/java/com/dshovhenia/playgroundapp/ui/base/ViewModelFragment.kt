package com.dshovhenia.playgroundapp.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dshovhenia.playgroundapp.AndroidApplication
import com.dshovhenia.playgroundapp.injection.component.ApplicationComponent
import javax.inject.Inject

abstract class ViewModelFragment : Fragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  protected inline fun <reified VM : ViewModel> viewModel(): Lazy<VM> =
    viewModels { viewModelFactory }

  override fun onAttach(context: Context) {
    val appComponent = (activity?.application as AndroidApplication).component
    inject(appComponent)
    super.onAttach(context)
  }

  /**
   * Inject dependencies
   */
  protected abstract fun inject(component: ApplicationComponent)

}
