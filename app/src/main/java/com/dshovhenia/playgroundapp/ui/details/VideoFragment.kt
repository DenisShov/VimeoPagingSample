package com.dshovhenia.playgroundapp.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Constraints
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.fragment_video.*
import kotlinx.android.synthetic.main.item_user.*
import kotlinx.android.synthetic.main.layout_expandable_text_view.*
import com.dshovhenia.playgroundapp.GlideApp
import com.dshovhenia.playgroundapp.R
import com.dshovhenia.playgroundapp.data.model.VimeoVideo
import com.dshovhenia.playgroundapp.ui.details.comments.CommentsPagerAdapter
import com.dshovhenia.playgroundapp.util.DisplayMetricsUtil
import com.dshovhenia.playgroundapp.util.VimeoTextUtil

class VideoFragment : Fragment() {

  private lateinit var pagerAdapter: CommentsPagerAdapter
  private lateinit var video: VimeoVideo
  private lateinit var screenDimensions: DisplayMetricsUtil.Dimensions

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    screenDimensions = DisplayMetricsUtil.screenDimensions

    video = arguments?.getParcelable(ARG_VIMEO_VIDEO)!!
    savedInstanceState?.apply {
      video = getParcelable(SAVED_VIMEO_VIDEO)!!
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_video, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initPager()
    initViews()
  }

  private fun initPager() {
    pagerAdapter = CommentsPagerAdapter(
      context!!, parentFragmentManager, video.metadata!!
    )
    video_viewpager.offscreenPageLimit = 2
    video_viewpager.adapter = pagerAdapter
    video_tablayout.setupWithViewPager(video_viewpager)
  }

  private fun initViews() {
    // Dynamically set the height of the video container
    val videoHeight: Int
    if (screenDimensions.width < screenDimensions.height) {
      // We're in portrait mode since screen width < height
      videoHeight = (screenDimensions.width / DisplayMetricsUtil.VIDEO_ASPECT_RATIO).toInt()
    } else {
      // We're in landscape mode
      videoHeight = screenDimensions.height - DisplayMetricsUtil.statusBarHeight
    }
    video_image.layoutParams =
      Constraints.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, videoHeight)

    // We need to set the top margin of the details Linear Layout to the calculated height of the video container
    val params = video_details_layout.layoutParams as CollapsingToolbarLayout.LayoutParams
    params.setMargins(0, videoHeight, 0, 0)
    video_details_layout.layoutParams = params

    video_timelength_text.text = VimeoTextUtil.formatSecondsToDuration(video.duration)
    video_title_text.text = video.name

    video.createdTime?.let {
      video_plays_text.text = VimeoTextUtil.formatVideoAgeAndPlays(video.stats?.plays, it)
    }

    VimeoTextUtil.hideOrDisplayTextViewIfNullString(
      layout_expandable_text, video.description
    )
    layout_expandable_text.setImageIcon(
      layout_expandable_image
    )
    layout_expandable_text.reinitialize()

    video.user?.pictures?.let {
      GlideApp.with(this@VideoFragment).load(it.sizes[2].link).circleCrop()
        .placeholder(R.drawable.user_image_placeholder).fallback(R.drawable.user_image_placeholder)
        .transition(DrawableTransitionOptions.withCrossFade()).into(item_user_imageview)
    }

    item_user_name_textview.text = video.user?.name ?: "no name"

    var videoCountAndFollowers = "0"
    if (video.user?.metadata?.videosConnection != null && video.user?.metadata?.followersConnection != null) {
      videoCountAndFollowers = VimeoTextUtil.formatVideoCountAndFollowers(
        video.user?.metadata?.videosConnection!!.total,
        video.user?.metadata?.followersConnection!!.total
      )
    }
    item_user_videosfollowers_textview.text = videoCountAndFollowers

    initWebView()
  }

  @SuppressLint("SetJavaScriptEnabled")
  fun initWebView() {
    val webplayerUrl =
      "https://player.vimeo.com/video/" + VimeoTextUtil.generateIdFromUri(video.uri)

    video_image.settings.javaScriptEnabled = true
    video_image.settings.javaScriptCanOpenWindowsAutomatically = true
    video_image.settings.mediaPlaybackRequiresUserGesture = false

    video_image.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
    CookieManager.getInstance().setAcceptThirdPartyCookies(video_image, true)

    video_image.webViewClient = object : WebViewClient() {
      override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
        if (url == webplayerUrl) {
          webView.loadUrl(url)
          return true
        }
        return false
      }
    }

    video_image.loadUrl(webplayerUrl)
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putParcelable(SAVED_VIMEO_VIDEO, video)
  }

  override fun onDestroyView() {
    video_viewpager.adapter = null

    if (!(context as AppCompatActivity).isFinishing) {
      Glide.with(this).clear(video_image)
      Glide.with(this).clear(item_user_imageview)
    }
    super.onDestroyView()
  }

  companion object {
    val FRAGMENT_VIDEO_TAG = "fragment_video"

    private val SAVED_VIMEO_VIDEO = "fragment_video_saved_video"
    private val ARG_VIMEO_VIDEO = "vimeo_video"

    fun newInstance(video: VimeoVideo): VideoFragment {
      val args = Bundle()
      args.putParcelable(ARG_VIMEO_VIDEO, video)

      val fragment = VideoFragment()
      fragment.arguments = args
      return fragment
    }
  }

}