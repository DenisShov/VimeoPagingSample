package com.dshovhenia.playgroundapp.ui.details.comments

import androidx.fragment.app.Fragment
import com.dshovhenia.playgroundapp.GlideApp
import com.dshovhenia.playgroundapp.R
import com.dshovhenia.playgroundapp.data.cache.model.comment.CachedComment
import com.dshovhenia.playgroundapp.data.cache.model.comment.RelationsComment
import com.dshovhenia.playgroundapp.databinding.ItemCommentBinding
import com.dshovhenia.playgroundapp.paging.base.ListItemViewHolder
import com.dshovhenia.playgroundapp.util.VimeoTextUtil
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
