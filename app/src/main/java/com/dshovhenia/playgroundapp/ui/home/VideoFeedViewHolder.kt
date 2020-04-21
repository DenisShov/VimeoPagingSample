package com.dshovhenia.playgroundapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.dshovhenia.playgroundapp.GlideApp
import com.dshovhenia.playgroundapp.R
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideo
import com.dshovhenia.playgroundapp.paging.base.list_item.ListItemViewHolder
import com.dshovhenia.playgroundapp.util.DisplayMetricsUtil.Dimensions
import com.dshovhenia.playgroundapp.util.VimeoTextUtil
import kotlinx.android.synthetic.main.item_video_feed.view.*

class VideoFeedViewHolder(
    baseFragment: Fragment,
    inflater: LayoutInflater,
    parent: ViewGroup,
    screenDimensions: Dimensions
) : ListItemViewHolder<CachedVideo>(
    baseFragment, inflater.inflate(R.layout.item_video_feed, parent, false)
) {

    init {
        itemView.video_feed_imageview.layoutParams =
            ConstraintLayout.LayoutParams(screenDimensions.width, screenDimensions.height)
    }

    override fun bind(listItem: CachedVideo, onItemClick: ((CachedVideo) -> Unit)?) {
        itemView.setOnClickListener { onItemClick?.invoke(listItem) }

        itemView.run {
            video_feed_title_textview.text = listItem.name
            video_feed_user_textview.text = listItem.user?.name ?: "no name"

            val totalLikes = listItem.metadata?.likesConnection?.total ?: 0
            video_feed_likes_textview.text = itemView.resources.getQuantityString(
                R.plurals.video_feed_likes_plural, totalLikes, totalLikes
            )
            video_feed_timelength_textview.text =
                VimeoTextUtil.formatSecondsToDuration(listItem.duration)

            listItem.pictures?.let {
                GlideApp.with(mBaseFragment).load(it.sizes[2].link)
                    .placeholder(R.drawable.video_image_placeholder)
                    .fallback(R.drawable.video_image_placeholder).fitCenter()
                    .into(video_feed_imageview)
            }
        }
    }
}
