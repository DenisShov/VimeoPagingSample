package com.dshovhenia.playgroundapp.ui.details.comments

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kotlinx.android.synthetic.main.fragment_comments.*
import kotlinx.android.synthetic.main.layout_message.*
import kotlinx.android.synthetic.main.layout_progress.*
import timber.log.Timber
import com.dshovhenia.playgroundapp.R
import com.dshovhenia.playgroundapp.data.model.VimeoComment
import com.dshovhenia.playgroundapp.data.model.VimeoConnection
import com.dshovhenia.playgroundapp.injection.component.ApplicationComponent
import com.dshovhenia.playgroundapp.paging.ResultState
import com.dshovhenia.playgroundapp.paging.VimeoPagedListAdapter
import com.dshovhenia.playgroundapp.paging.base.list_item.ListItemViewHolder
import com.dshovhenia.playgroundapp.paging.base.list_item.item_decoration.MarginDividerItemDecoration
import com.dshovhenia.playgroundapp.ui.base.ViewModelFragment
import com.dshovhenia.playgroundapp.util.DisplayMetricsUtil

class CommentsFragment : ViewModelFragment() {

  private lateinit var mConnection: VimeoConnection
  private lateinit var pagerAdapter: VimeoPagedListAdapter<VimeoComment>

  private val vm by viewModel<CommentsViewModel>()

  override fun inject(component: ApplicationComponent) {
    component.inject(this)
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    mConnection = arguments?.getParcelable<Parcelable>(ARG_VIMEO_CONNECTION) as VimeoConnection
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState != null) {
      mConnection = savedInstanceState.getParcelable(SAVED_VIMEO_CONNECTION)!!
    }
    pagerAdapter = VimeoPagedListAdapter(
      this,
      object : ListItemViewHolder.ListItemViewHolderGenerator<VimeoComment> {
        override fun generateViewHolder(

          baseFragment: Fragment, inflater: LayoutInflater, parent: ViewGroup
        ): ListItemViewHolder<VimeoComment> {
          return CommentViewHolder(
            baseFragment, inflater, parent
          )
        }
      })
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_comments, container, false);
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews()
    if (pagerAdapter.isEmpty) {
      vm.onInitialListRequested(mConnection.uri)
    }
  }

  private fun initViews() {
    recyclerview.itemAnimator = DefaultItemAnimator()
    recyclerview.layoutManager = setUpLayoutManager(context, DisplayMetricsUtil.isTabletLayout)
    recyclerview.addItemDecoration(
      MarginDividerItemDecoration(
        context!!, LinearLayoutManager.VERTICAL, R.dimen.recycler_view_divider_margin
      )
    )
    recyclerview.adapter = pagerAdapter

    message_tryagain_button.setOnClickListener {
      vm.retry()
    }

    vm.commentListLiveData.observe(viewLifecycleOwner, Observer<PagedList<VimeoComment>> {
      pagerAdapter.submitList(it)
    })

    vm.stateLiveData.observe(viewLifecycleOwner, Observer { state ->
      when (state) {
        ResultState.LOADING_INITIAL -> {
          showMessageLayout(false)
          showProgress()
        }
        ResultState.SUCCESS -> hideProgress()
        ResultState.NO_DATA -> showEmpty()
        ResultState.ERROR -> {
          hideProgress()
          showError("An error occured")
        }
        else -> {
          Timber.e("Not expected ResultState")
        }
      }
    })
  }

  private fun setUpLayoutManager(context: Context?, isTabletLayout: Boolean): LayoutManager? {
    return if (!isTabletLayout) {
      LinearLayoutManager(context)
    } else {
      GridLayoutManager(context, DisplayMetricsUtil.TAB_LAYOUT_SPAN_SIZE)
    }
  }

  fun showProgress() {
    if (pagerAdapter.isEmpty) {
      progress_bar.visibility = View.VISIBLE
    }
  }

  fun hideProgress() {
    progress_bar.visibility = View.GONE
  }

  fun showError(errorMessage: String) {
    message_imageview.setImageResource(R.drawable.ic_error_outline_black_48dp)
    message_textview.text = getString(R.string.error_generic_server_error, errorMessage)
    message_tryagain_button.text = getString(R.string.action_try_again)
    showMessageLayout(true)
  }

  fun showEmpty() {
    message_imageview.setImageResource(R.drawable.ic_clear_black_48dp)
    message_textview.text = getString(R.string.error_no_comments_to_display)
    message_tryagain_button.text = getString(R.string.action_check_again)
    showMessageLayout(true)
  }

  fun showMessageLayout(show: Boolean) {
    layout_message.visibility = if (show) View.VISIBLE else View.GONE
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putParcelable(SAVED_VIMEO_CONNECTION, mConnection)
  }

  companion object {
    private val SAVED_VIMEO_CONNECTION = "fragment_video_tab_connection"
    private val ARG_VIMEO_CONNECTION = "vimeo_connection"

    fun newInstance(connection: VimeoConnection): CommentsFragment {
      val args = Bundle()
      args.putParcelable(ARG_VIMEO_CONNECTION, connection)

      val fragment = CommentsFragment()
      fragment.arguments = args
      return fragment
    }
  }

}