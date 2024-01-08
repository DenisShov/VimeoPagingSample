package com.dshovhenia.vimeo.paging.sample.data.model.video

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class VideoStats(var plays: Long = 0) : Parcelable
