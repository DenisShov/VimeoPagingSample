package com.dshovhenia.playgroundapp.data.cache.model.video

import androidx.room.Embedded
import androidx.room.Relation
import com.dshovhenia.playgroundapp.data.cache.model.pictures.CachedPictureSizes
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUser
import com.dshovhenia.playgroundapp.data.cache.model.user.RelationsUser

data class RelationsVideo(
  @Embedded
  val video: CachedVideo,
  @Relation(
    parentColumn = "id",
    entityColumn = "videoId",
    entity = CachedUser::class
  )
  var relationsUser: RelationsUser? = null,
  @Relation(
    parentColumn = "id",
    entityColumn = "videoId",
    entity = CachedPictureSizes::class
  )
  var relationsPictureSizes: List<CachedPictureSizes> = ArrayList()
)
