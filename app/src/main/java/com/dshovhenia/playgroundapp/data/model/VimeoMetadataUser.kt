package com.dshovhenia.playgroundapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class VimeoMetadataUser(
  var followersConnection: VimeoConnection? = null, var videosConnection: VimeoConnection? = null
) : Parcelable