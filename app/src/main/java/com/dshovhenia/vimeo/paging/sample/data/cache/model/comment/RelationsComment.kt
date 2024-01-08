package com.dshovhenia.vimeo.paging.sample.data.cache.model.comment

import androidx.room.Embedded
import androidx.room.Relation
import com.dshovhenia.vimeo.paging.sample.data.cache.model.user.CachedUser
import com.dshovhenia.vimeo.paging.sample.data.cache.model.user.RelationsUser

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
