package com.dshovhenia.playgroundapp.data.model.pictures

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PictureSizes(
  var width: Int = 0, var height: Int = 0, var link: String = ""
) : Parcelable