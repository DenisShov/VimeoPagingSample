package com.dshovhenia.playgroundapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_message.*
import kotlinx.android.synthetic.main.layout_progress.*
import kotlinx.android.synthetic.main.toolbar.view.*
import com.dshovhenia.playgroundapp.R
import com.dshovhenia.playgroundapp.data.model.VimeoVideo
import com.dshovhenia.playgroundapp.injection.component.ApplicationComponent
import com.dshovhenia.playgroundapp.paging.ResultState
import com.dshovhenia.playgroundapp.paging.VimeoPagedListAdapter
import com.dshovhenia.playgroundapp.paging.base.list_item.ListItemViewHolder
import com.dshovhenia.playgroundapp.paging.base.list_item.item_decoration.MarginDividerItemDecoration
import com.dshovhenia.playgroundapp.ui.base.ViewModelFragment
import com.dshovhenia.playgroundapp.util.DisplayMetricsUtil
import com.dshovhenia.playgroundapp.util.KeyboardUtil

class HomeFragment : ViewModelFragment() {

  private val vm by viewModel<HomeViewModel>()

  private lateinit var pagerAdapter: VimeoPagedListAdapter<VimeoVideo>
  private lateinit var screenDimensions: DisplayMetricsUtil.Dimensions

  override fun inject(component: ApplicationComponent) {
    component.inject(this)
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    screenDimensions = DisplayMetricsUtil.screenDimensions
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    setHasOptionsMenu(true)
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_home, container, false)
    (activity as AppCompatActivity).setSupportActionBar(view.toolbar)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews()
    initState()
    vm.showStaffPickVideos()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      android.R.id.home -> {
        vm.showStaffPickVideos()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.menu_search, menu)

    val searchItem = menu.findItem(R.id.action_search)
    val searchView = searchItem.actionView as SearchView

    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String): Boolean {
        activity?.also { KeyboardUtil.hideKeyboard(it) }
        vm.searchVideos(query)
        return true
      }

      override fun onQueryTextChange(queryText: String): Boolean {
        if (queryText.isEmpty()) {
          vm.showStaffPickVideos()
        }
        return true
      }
    })
  }

  private fun initViews() {
    val isTabletLayout = DisplayMetricsUtil.isTabletLayout

    pagerAdapter = VimeoPagedListAdapter(
      this,
      object : ListItemViewHolder.ListItemViewHolderGenerator<VimeoVideo> {
        override fun generateViewHolder(
          baseFragment: Fragment, inflater: LayoutInflater, parent: ViewGroup
        ): ListItemViewHolder<VimeoVideo> {
          return VideoFeedViewHolder(
            baseFragment,
            inflater,
            parent,
            DisplayMetricsUtil.getDimensToFillWidth(screenDimensions, isTabletLayout)
          )
        }
      })

    recyclerview.setBackgroundColor(resources.getColor(R.color.mediumLightGray, null))
    recyclerview.itemAnimator = DefaultItemAnimator()
    recyclerview.layoutManager = setUpLayoutManager(context, isTabletLayout)
    recyclerview.addItemDecoration(
      MarginDividerItemDecoration(
        context!!, LinearLayoutManager.VERTICAL, R.dimen.recycler_view_divider_margin
      )
    )
    recyclerview.adapter = pagerAdapter

    fragment_home_swipe_to_refresh.setProgressBackgroundColorSchemeResource(R.color.colorPrimaryDark)
    fragment_home_swipe_to_refresh.setColorSchemeResources(R.color.white)
    fragment_home_swipe_to_refresh.setOnRefreshListener {
      vm.invalidateDataSource()
    }

    vm.videoListLiveData.observe(viewLifecycleOwner, Observer<PagedList<VimeoVideo>> {
      pagerAdapter.submitList(it)
    })
  }

  private fun setUpLayoutManager(
    context: Context?, isTabletLayout: Boolean
  ): RecyclerView.LayoutManager? {
    return if (!isTabletLayout) {
      LinearLayoutManager(context)
    } else {
      GridLayoutManager(context, DisplayMetricsUtil.TAB_LAYOUT_SPAN_SIZE)
    }
  }

  private fun initState() {
    message_tryagain_button.setOnClickListener { vm.retry() }

    vm.stateLiveData.observe(viewLifecycleOwner, Observer { state ->
      when (state) {
        ResultState.LOADING_INITIAL -> {
          showProgress()
        }
        ResultState.SUCCESS -> hideProgress()
        ResultState.NO_DATA -> {
          hideProgress()
          showEmpty()
        }
        ResultState.ERROR -> {
          hideProgress()
          showError("An error occurred")
        }
        else -> {
          // do nothing
        }
      }
    })
  }

  override fun onDestroyView() {
    fragment_home_swipe_to_refresh.setOnRefreshListener(null)
    recyclerview.adapter = null
    super.onDestroyView()
  }

  fun showProgress() {
    showMessageLayout(false)
    if (pagerAdapter.isEmpty && !fragment_home_swipe_to_refresh.isRefreshing) {
      progress_bar.visibility = View.VISIBLE
    }
  }

  fun hideProgress() {
    fragment_home_swipe_to_refresh.isRefreshing = false
    progress_bar.visibility = View.GONE
  }

  private fun showError(errorMessage: String) {
    message_imageview.setImageResource(R.drawable.ic_error_outline_black_48dp)
    message_textview.text = getString(R.string.error_generic_server_error, errorMessage)
    message_tryagain_button.text = getString(R.string.action_try_again)
    showMessageLayout(true)
  }

  private fun showEmpty() {
    message_imageview.setImageResource(R.drawable.ic_clear_black_48dp)
    message_textview.text = getString(R.string.error_no_videos_to_display)
    message_tryagain_button.text = getString(R.string.action_check_again)
    showMessageLayout(true)
  }

  private fun showMessageLayout(show: Boolean) {
    layout_message.visibility = if (show) View.VISIBLE else View.GONE
  }

}