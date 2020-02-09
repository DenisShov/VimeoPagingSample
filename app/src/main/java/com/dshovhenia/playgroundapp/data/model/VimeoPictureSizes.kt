package com.dshovhenia.playgroundapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class VimeoPictureSizes(
  var width: Int = 0, var height: Int = 0, var link: String = ""
) : Parcelable
