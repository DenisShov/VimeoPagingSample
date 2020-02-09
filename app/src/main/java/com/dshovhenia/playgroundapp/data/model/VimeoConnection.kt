package com.dshovhenia.playgroundapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class VimeoConnection(
    var uri: String = "", var total: Int = 0
) : Parcelable