package com.dshovhenia.vimeo.paging.sample.paging.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class ListItemViewHolder<T>(var mBaseFragment: Fragment, itemView: View) :
    ViewHolder(itemView) {

    abstract fun bind(listItem: T, onItemClick: ((T) -> Unit)?)

    /**
     * Interface for generating new ViewHolders
     */
    interface ListItemViewHolderGenerator<T> {
        fun generateViewHolder(
            baseFragment: Fragment, inflater: LayoutInflater, parent: ViewGroup
        ): ListItemViewHolder<T>
    }
}
