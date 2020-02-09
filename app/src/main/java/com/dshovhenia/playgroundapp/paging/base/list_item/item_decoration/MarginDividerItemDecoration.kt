package com.dshovhenia.playgroundapp.paging.base.list_item.item_decoration

import android.content.Context
import android.graphics.drawable.InsetDrawable
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.DividerItemDecoration

class MarginDividerItemDecoration(context: Context, orientation: Int, @DimenRes id: Int) :
  DividerItemDecoration(context, orientation) {

  init {
    val ATTRS = intArrayOf(android.R.attr.listDivider)

    val a = context.obtainStyledAttributes(ATTRS)
    val divider = a.getDrawable(0)
    val inset = context.resources.getDimensionPixelSize(id)
    val insetDivider = InsetDrawable(divider, inset, 0, inset, 0)
    a.recycle()

    setDrawable(insetDivider)
  }
}
