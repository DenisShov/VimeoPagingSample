package com.dshovhenia.playgroundapp.ui.home

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.dshovhenia.playgroundapp.GlideApp
import com.dshovhenia.playgroundapp.R
import com.dshovhenia.playgroundapp.data.cache.mapper.relations_mapper.RelationsVideoMapper
import com.dshovhenia.playgroundapp.data.cache.model.video.RelationsVideo
import com.dshovhenia.playgroundapp.databinding.ItemVideoFeedBinding
import com.dshovhenia.playgroundapp.paging.base.ListItemViewHolder
import com.dshovhenia.playgroundapp.util.DisplayMetricsUtil.Dimensions
import com.dshovhenia.playgroundapp.util.VimeoTextUtil

class VideoFeedViewHolder(
  baseFragment: Fragment,
  private val itemVideoFeedBinding: ItemVideoFeedBinding,
  private val relationsVideoMapper: RelationsVideoMapper,
  screenDimensions: Dimensions
) : ListItemViewHolder<RelationsVideo>(baseFragment, itemVideoFeedBinding.root) {

  init {
    itemVideoFeedBinding.videoImageView.layoutParams =
      ConstraintLayout.LayoutParams(screenDimensions.width, screenDimensions.height)
  }

  override fun bind(listItem: RelationsVideo, onItemClick: ((RelationsVideo) -> Unit)?) {
    val video = relationsVideoMapper.mapFrom(listItem)

    itemView.setOnClickListener { onItemClick?.invoke(listItem) }

    itemVideoFeedBinding.apply {
      videoTitleTextView.text = video.name
      userTextView.text = video.user?.name ?: "no name"

      val totalLikes = video.likesTotal
      likesTextView.text = itemView.resources.getQuantityString(
        R.plurals.video_feed_likes_plural, totalLikes, totalLikes
      )
      timeLengthTextView.text =
        VimeoTextUtil.formatSecondsToDuration(video.duration)

      if (video.pictureSizes.size > 2) {
        GlideApp.with(mBaseFragment)
          .load(video.pictureSizes[2].link)
          .placeholder(R.drawable.video_image_placeholder)
          .fallback(R.drawable.video_image_placeholder)
          .fitCenter()
          .into(videoImageView)
      }
    }
  }
}
