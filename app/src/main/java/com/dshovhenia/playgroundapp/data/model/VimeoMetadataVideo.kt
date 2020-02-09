package com.dshovhenia.playgroundapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class VimeoMetadataVideo(
  var commentsConnection: VimeoConnection? = null, var likesConnection: VimeoConnection? = null
) : Parcelable