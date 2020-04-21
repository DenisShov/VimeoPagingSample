package com.dshovhenia.playgroundapp.data.model.user

import android.os.Parcelable
import com.dshovhenia.playgroundapp.data.model.Connection
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserMetadata(
  var followersConnection: Connection? = null, var videosConnection: Connection? = null
) : Parcelable