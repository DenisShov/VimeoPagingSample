package com.dshovhenia.vimeo.paging.sample.data.cache.model.user

import androidx.room.Embedded
import androidx.room.Relation
import com.dshovhenia.vimeo.paging.sample.data.cache.model.pictures.CachedPictureSizes

data class RelationsUser(
    @Embedded
  val user: CachedUser,
    @Relation(
    parentColumn = "id",
    entityColumn = "userId",
    entity = CachedPictureSizes::class
  )
  val pictureSizes: List<CachedPictureSizes>
)
