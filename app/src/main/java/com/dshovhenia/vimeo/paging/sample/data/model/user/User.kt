package com.dshovhenia.vimeo.paging.sample.data.model.user

import android.os.Parcelable
import com.dshovhenia.vimeo.paging.sample.data.model.pictures.Pictures
import kotlinx.parcelize.Parcelize

@Parcelize
class User(
    var name: String = "", var pictures: Pictures?, var metadata: UserMetadata?
) : Parcelable
