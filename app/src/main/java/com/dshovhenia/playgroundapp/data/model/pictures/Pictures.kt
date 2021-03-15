package com.dshovhenia.playgroundapp.data.model.pictures

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Pictures(var sizes: List<PictureSizes> = ArrayList()) :
  Parcelable
