package com.dshovhenia.playgroundapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class VimeoUser(
  var name: String = "", var pictures: VimeoPictures?, var metadata: VimeoMetadataUser?
) : Parcelable
