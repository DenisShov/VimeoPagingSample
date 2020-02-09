package com.dshovhenia.playgroundapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class VimeoComment(
  var uri: String = "",
  var text: String = "",
  var created_on: Date? = null,
  var user: VimeoUser? = null
) : Parcelable
