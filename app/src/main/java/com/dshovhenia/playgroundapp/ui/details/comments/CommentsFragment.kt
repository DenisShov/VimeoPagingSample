package com.dshovhenia.playgroundapp.ui.details.comments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.dshovhenia.playgroundapp.R
import com.dshovhenia.playgroundapp.data.cache.model.comment.RelationsComment
import com.dshovhenia.playgroundapp.databinding.FragmentCommentsBinding
import com.dshovhenia.playgroundapp.databinding.ItemCommentBinding
import com.dshovhenia.playgroundapp.paging.VimeoPagedListAdapter
import com.dshovhenia.playgroundapp.paging.base.ListItemViewHolder
import com.dshovhenia.playgroundapp.paging.base.MarginDividerItemDecoration
import com.dshovhenia.playgroundapp.paging.load_state.ExampleLoadStateAdapter
import com.dshovhenia.playgroundapp.ui.base.BaseFragment
import com.dshovhenia.playgroundapp.util.DisplayMetricsUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : BaseFragment<FragmentCommentsBinding>() {

  private lateinit var mCommentUrl: String
  private lateinit var pagerAdapter: VimeoPagedListAdapter<RelationsComment>

  private val vm by viewModels<CommentsViewModel>()

  override fun bindView(layoutInflater: LayoutInflater) =
    FragmentCommentsBinding.inflate(layoutInflater)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mCommentUrl = if (savedInstanceState != null) {
      savedInstanceState.getString(SAVED_VIMEO_CONNECTION)!!
    } else {
      arguments?.getString(ARG_VIMEO_CONNECTION)!!
    }

    vm.onInitialListRequested(mCommentUrl)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews()
  }

  private fun initViews() {
    pagerAdapter = VimeoPagedListAdapter(
      this,
      object : ListItemViewHolder.ListItemViewHolderGenerator<RelationsComment> {
        override fun generateViewHolder(
          baseFragment: Fragment, inflater: LayoutInflater, parent: ViewGroup
        ): ListItemViewHolder<RelationsComment> {
          val layoutInflater = LayoutInflater.from(parent.context)
          val itemCommentBinding = ItemCommentBinding.inflate(layoutInflater, parent, false)
          return CommentViewHolder(baseFragment, itemCommentBinding)
        }
      },
      object : DiffUtil.ItemCallback<RelationsComment>() {
        override fun areItemsTheSame(
          oldItem: RelationsComment,
          newItem: RelationsComment
        ): Boolean {
          return oldItem.comment.id == newItem.comment.id
        }

        override fun areContentsTheSame(
          oldItem: RelationsComment,
          newItem: RelationsComment
        ): Boolean {
          return oldItem.comment.uri == newItem.comment.uri &&
                  oldItem.comment.text == newItem.comment.text
        }
      }
    )
    pagerAdapter.addLoadStateListener { loadState ->
      when (loadState.refresh) {
        is LoadState.Loading -> {
          showMessageLayout(false)
          showProgress()
        }
        is LoadState.NotLoading -> {
          hideProgress()
        }
        is LoadState.Error -> {
          hideProgress()
          showError()
        }
      }
    }

    binding.recyclerview.apply {
      itemAnimator = DefaultItemAnimator()
      layoutManager = setUpLayoutManager(context, DisplayMetricsUtil.isTabletLayout)
      addItemDecoration(
        MarginDividerItemDecoration(
          context, LinearLayoutManager.VERTICAL, R.dimen.recycler_view_divider_margin
        )
      )
      adapter = pagerAdapter.withLoadStateFooter(ExampleLoadStateAdapter(pagerAdapter::retry))
    }

    binding.messageLayout.tryAgainButton.setOnClickListener {
      pagerAdapter.retry()
    }

    vm.commentResult.observe(viewLifecycleOwner, Observer<PagingData<RelationsComment>> {
      pagerAdapter.submitData(lifecycle, it)
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
      binding.progressLayout.progressBar.visibility = View.VISIBLE
    }
  }

  fun hideProgress() {
    binding.progressLayout.progressBar.visibility = View.GONE
  }

  fun showError() {
    binding.messageLayout.apply {
      icon.setImageResource(R.drawable.ic_error_outline_black_48dp)
      message.text = getString(R.string.error_generic_server_error)
      tryAgainButton.text = getString(R.string.retry)
    }
    showMessageLayout(true)
  }

  fun showMessageLayout(show: Boolean) {
    binding.messageLayout.messageRoot.visibility = if (show) View.VISIBLE else View.GONE
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putString(SAVED_VIMEO_CONNECTION, mCommentUrl)
  }

  companion object {
    private val SAVED_VIMEO_CONNECTION = "saved_video_connection"
    private val ARG_VIMEO_CONNECTION = "vimeo_connection"

    fun newInstance(commentUrl: String): CommentsFragment {
      val args = Bundle()
      args.putString(ARG_VIMEO_CONNECTION, commentUrl)

      val fragment = CommentsFragment()
      fragment.arguments = args
      return fragment
    }
  }

}