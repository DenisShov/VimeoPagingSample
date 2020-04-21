package com.dshovhenia.playgroundapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Paging(
  var next: String = ""
) : Parcelable