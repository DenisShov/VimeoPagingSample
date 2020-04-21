package com.dshovhenia.playgroundapp.data.cache.model.comment

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUser
import java.util.*

@Entity(tableName = DbConstants.COMMENT_TABLE_NAME)
data class CachedComment(
  @PrimaryKey
  var uri: String,
  var text: String,
  var created_on: Date?,
  var user: CachedUser?,
  val createdTimestamp: Long = System.currentTimeMillis()
)