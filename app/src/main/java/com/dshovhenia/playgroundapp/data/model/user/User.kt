package com.dshovhenia.playgroundapp.data.model.user

import android.os.Parcelable
import com.dshovhenia.playgroundapp.data.model.pictures.Pictures
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
  var name: String = "", var pictures: Pictures?, var userMetadata: UserMetadata?
) : Parcelable
