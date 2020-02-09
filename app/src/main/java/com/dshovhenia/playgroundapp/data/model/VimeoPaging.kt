package com.dshovhenia.playgroundapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class VimeoPaging(
  var next: String = ""
) : Parcelable