package com.dshovhenia.playgroundapp.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.dshovhenia.playgroundapp.R
import com.dshovhenia.playgroundapp.paging.base.list_item.ListItemViewHolder

class ProgressBarViewHolder<T>(
  baseFragment: Fragment, inflater: LayoutInflater, parent: ViewGroup
) : ListItemViewHolder<T>(baseFragment, inflater.inflate(R.layout.item_progress_bar, parent, false)) {
  val mProgressBar: ProgressBar
  override fun bind(listItem: T, onItemClick: ((T) -> Unit)?) {}

  init { //super(view);
    mProgressBar = itemView.findViewById<View>(R.id.item_progress_bar) as ProgressBar
  }
}