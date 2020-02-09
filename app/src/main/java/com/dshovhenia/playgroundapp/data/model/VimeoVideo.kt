package com.dshovhenia.playgroundapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.TypeParceler
import com.dshovhenia.playgroundapp.data.model.parceler.DateParceler
import java.util.*

@Parcelize
@TypeParceler<Date, DateParceler>
class VimeoVideo(
  var uri: String = "",
  var name: String = "",
  var description: String = "",
  var duration: Int = 0,
  var createdTime: Date?,
  var user: VimeoUser?,
  var pictures: VimeoPictures?,
  var metadata: VimeoMetadataVideo?,
  var stats: VimeoStats?
) : Parcelable
