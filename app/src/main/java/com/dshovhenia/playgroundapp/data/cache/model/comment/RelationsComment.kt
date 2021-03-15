package com.dshovhenia.playgroundapp.data.cache.model.comment

import androidx.room.Embedded
import androidx.room.Relation
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUser
import com.dshovhenia.playgroundapp.data.cache.model.user.RelationsUser

data class RelationsComment(
  @Embedded
  val comment: CachedComment,
  @Relation(
    parentColumn = "id",
    entityColumn = "commentId",
    entity = CachedUser::class
  )
  val relationsUser: RelationsUser
)