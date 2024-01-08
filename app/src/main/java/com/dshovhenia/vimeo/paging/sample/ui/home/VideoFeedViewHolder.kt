package com.dshovhenia.vimeo.paging.sample.ui.home

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.dshovhenia.vimeo.paging.sample.GlideApp
import com.dshovhenia.vimeo.paging.sample.R
import com.dshovhenia.vimeo.paging.sample.data.cache.mapper.relationsMapper.RelationsVideoMapper
import com.dshovhenia.vimeo.paging.sample.data.cache.model.video.RelationsVideo
import com.dshovhenia.vimeo.paging.sample.databinding.ItemVideoFeedBinding
import com.dshovhenia.vimeo.paging.sample.paging.base.ListItemViewHolder
import com.dshovhenia.vimeo.paging.sample.util.DisplayMetricsUtil.Dimensions
import com.dshovhenia.vimeo.paging.sample.util.VimeoTextUtil

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
