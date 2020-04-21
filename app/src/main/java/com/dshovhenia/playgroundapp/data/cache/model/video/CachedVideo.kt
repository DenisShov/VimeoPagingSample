package com.dshovhenia.playgroundapp.data.cache.model.video

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.pictures.CachedPictures
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUser
import java.util.*

@Entity(tableName = DbConstants.VIDEO_TABLE_NAME)
class CachedVideo(
  @PrimaryKey
  val uri: String,
  var name: String,
  var description: String,
  var duration: Int,
  var createdTime: Date?,
  var user: CachedUser?,
  var pictures: CachedPictures?,
  var metadata: CachedVideoMetadata?,
  var stats: CachedVideoStats?,
  val createdTimestamp: Long = System.currentTimeMillis()
)
