package com.dshovhenia.playgroundapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Collection<T : Parcelable>(
  var paging: Paging? = null,
  var data: List<T> = ArrayList()
) : Parcelable

