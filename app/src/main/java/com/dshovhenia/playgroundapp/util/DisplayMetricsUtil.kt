package com.dshovhenia.playgroundapp.util

import android.content.res.Resources

object DisplayMetricsUtil {

  class Dimensions(val width: Int, val height: Int)

  val TAB_LAYOUT_SPAN_SIZE = 2
  var VIDEO_ASPECT_RATIO = 16.toFloat() / 9
  private val SCREEN_TABLET_DP_WIDTH = 600

  val isTabletLayout: Boolean
    get() {
      val displayMetrics = Resources.getSystem().displayMetrics
      val screenWidth = displayMetrics.widthPixels / displayMetrics.density
      return screenWidth >= SCREEN_TABLET_DP_WIDTH
    }

  val screenDimensions: Dimensions
    get() {
      val displayMetrics = Resources.getSystem().displayMetrics
      return Dimensions(displayMetrics.widthPixels, displayMetrics.heightPixels)
    }

  val statusBarHeight: Int
    get() {
      var result = 0
      val resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")
      if (resourceId > 0) {
        result = Resources.getSystem().getDimensionPixelSize(resourceId)
      }
      return result
    }

  fun getDimensToFillWidth(
    screenDimensions: Dimensions, isTabletLayout: Boolean
  ): Dimensions {
    val width = if (isTabletLayout) screenDimensions.width / TAB_LAYOUT_SPAN_SIZE
    else screenDimensions.width

    val height = (width / VIDEO_ASPECT_RATIO).toInt()
    return Dimensions(width, height)
  }

}