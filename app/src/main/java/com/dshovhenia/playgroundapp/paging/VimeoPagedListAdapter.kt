package com.dshovhenia.playgroundapp.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.dshovhenia.playgroundapp.paging.base.list_item.ListItemViewHolder
import com.dshovhenia.playgroundapp.paging.base.list_item.ListItemViewHolder.ListItemViewHolderGenerator

class VimeoPagedListAdapter<T>(
  private val mBaseFragment: Fragment,
  private val mViewHolderGenerator: ListItemViewHolderGenerator<T>
) : PagedListAdapter<T, ListItemViewHolder<T>>(DIFF_CALLBACK<T>()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder<T> {
    val layoutInflater = LayoutInflater.from(parent.context)
    return mViewHolderGenerator.generateViewHolder(mBaseFragment, layoutInflater, parent)
  }

  override fun onBindViewHolder(holder: ListItemViewHolder<T>, position: Int) {
    val item = getItem(position)
    if (item != null) {
      holder.bind(item)
    }
  }

  val isEmpty: Boolean
    get() = itemCount == 0

  private class DIFF_CALLBACK<T> : ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
      return oldItem == newItem
    }
  }

}