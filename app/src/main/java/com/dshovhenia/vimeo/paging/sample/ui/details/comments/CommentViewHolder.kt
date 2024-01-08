package com.dshovhenia.vimeo.paging.sample.ui.details.comments

import androidx.fragment.app.Fragment
import com.dshovhenia.vimeo.paging.sample.GlideApp
import com.dshovhenia.vimeo.paging.sample.R
import com.dshovhenia.vimeo.paging.sample.data.cache.model.comment.CachedComment
import com.dshovhenia.vimeo.paging.sample.data.cache.model.comment.RelationsComment
import com.dshovhenia.vimeo.paging.sample.databinding.ItemCommentBinding
import com.dshovhenia.vimeo.paging.sample.paging.base.ListItemViewHolder
import com.dshovhenia.vimeo.paging.sample.util.VimeoTextUtil
import java.util.*

class CommentViewHolder(
  baseFragment: Fragment,
  private val itemCommentBinding: ItemCommentBinding
) : ListItemViewHolder<RelationsComment>(baseFragment, itemCommentBinding.root) {

  override fun bind(listItem: RelationsComment, onItemClick: ((RelationsComment) -> Unit)?) {
    val comment = CachedComment().initialize(listItem)

    itemCommentBinding.apply {
      userNameTextView.text = comment.user!!.name
      comment.created_on?.let {
        val commentAge = String.format(
          Locale.getDefault(),
          " ⋅ %s",
          VimeoTextUtil.dateCreatedToTimePassed(comment.created_on!!)
        )
        ageTextView.text = commentAge
      }

      commentTextView.text = comment.text

      GlideApp.with(mBaseFragment).load(comment.user!!.pictureSizes[1].link)
        .placeholder(R.drawable.user_image_placeholder)
        .fallback(R.drawable.user_image_placeholder)
        .circleCrop().into(userImageView)
    }
  }

}
