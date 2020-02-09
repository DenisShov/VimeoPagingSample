package com.dshovhenia.playgroundapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class VimeoPictures(var uri: String = "", var sizes: List<VimeoPictureSizes> = ArrayList()) :
  Parcelable
