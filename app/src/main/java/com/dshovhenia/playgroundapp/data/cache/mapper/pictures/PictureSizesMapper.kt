package com.dshovhenia.playgroundapp.data.cache.mapper.pictures

import com.dshovhenia.playgroundapp.data.cache.mapper.Mapper
import com.dshovhenia.playgroundapp.data.cache.model.pictures.CachedPictureSizes
import com.dshovhenia.playgroundapp.data.model.pictures.PictureSizes
import javax.inject.Inject

class PictureSizesMapper @Inject constructor() : Mapper<CachedPictureSizes, PictureSizes> {

  override fun mapFrom(type: CachedPictureSizes?): PictureSizes? {
    return type?.let { PictureSizes(it.width, it.height, it.link) }
  }

  override fun mapTo(type: PictureSizes?): CachedPictureSizes? {
    return type?.let { CachedPictureSizes(it.width, it.height, it.link) }
  }
}