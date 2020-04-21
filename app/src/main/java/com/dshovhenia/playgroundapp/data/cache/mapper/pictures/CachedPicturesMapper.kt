package com.dshovhenia.playgroundapp.data.cache.mapper.pictures

import com.dshovhenia.playgroundapp.data.cache.mapper.Mapper
import com.dshovhenia.playgroundapp.data.cache.model.pictures.CachedPictures
import com.dshovhenia.playgroundapp.data.model.pictures.Pictures
import javax.inject.Inject

class CachedPicturesMapper @Inject constructor(private val pictureSizesMapper: PictureSizesMapper) :
  Mapper<CachedPictures, Pictures> {

  override fun mapFrom(type: CachedPictures?): Pictures? {
    return type?.let { Pictures(type.uri, type.sizes.map { pictureSizesMapper.mapFrom(it)!! }) }
  }

  override fun mapTo(type: Pictures?): CachedPictures? {
    return type?.let { CachedPictures(type.uri, type.sizes.map { pictureSizesMapper.mapTo(it)!! }) }
  }
}