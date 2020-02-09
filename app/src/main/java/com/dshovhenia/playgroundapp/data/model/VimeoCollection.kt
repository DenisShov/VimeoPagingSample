package com.dshovhenia.playgroundapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class VimeoCollection<T : Parcelable>(
  var paging: VimeoPaging? = null,
  var data: List<T> = ArrayList()
) : Parcelable

