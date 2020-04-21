package com.dshovhenia.playgroundapp.data.model.pictures

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Pictures(var uri: String = "", var sizes: List<PictureSizes> = ArrayList()) :
  Parcelable
