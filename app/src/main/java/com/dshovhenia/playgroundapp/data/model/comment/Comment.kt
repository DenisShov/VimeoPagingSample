package com.dshovhenia.playgroundapp.data.model.comment

import android.os.Parcelable
import com.dshovhenia.playgroundapp.data.model.user.User
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Comment(
  var uri: String = "",
  var text: String = "",
  var created_on: Date? = null,
  var user: User? = null
) : Parcelable
