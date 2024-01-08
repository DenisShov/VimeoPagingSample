package com.dshovhenia.vimeo.paging.sample.data.model.pictures

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Pictures(var sizes: List<PictureSizes> = ArrayList()) : Parcelable
