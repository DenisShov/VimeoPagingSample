package com.dshovhenia.vimeo.paging.sample.data.cache.mapper.pictures

import com.dshovhenia.vimeo.paging.sample.data.cache.mapper.Mapper
import com.dshovhenia.vimeo.paging.sample.data.cache.model.pictures.CachedPictureSizes
import com.dshovhenia.vimeo.paging.sample.data.model.pictures.PictureSizes
import javax.inject.Inject

class PictureSizesMapper @Inject constructor() : Mapper<CachedPictureSizes, PictureSizes> {

  override fun mapFrom(type: CachedPictureSizes) =
    PictureSizes(type.width, type.height, type.link)

  override fun mapTo(type: PictureSizes) =
    CachedPictureSizes(type.width, type.height, type.link)
}
