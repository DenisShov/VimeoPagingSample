package com.dshovhenia.playgroundapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Connection(
  var uri: String = "", var total: Int = 0
) : Parcelable
