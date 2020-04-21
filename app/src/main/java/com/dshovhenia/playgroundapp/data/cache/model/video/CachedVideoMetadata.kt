package com.dshovhenia.playgroundapp.data.cache.model.video

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.connection.CachedConnection

@Entity(
  tableName = DbConstants.VIDEO_METADATA_TABLE_NAME,
  foreignKeys = [
    ForeignKey(
      entity = CachedVideo::class,
      parentColumns = ["uri"],
      childColumns = ["videoId"],
      onDelete = ForeignKey.CASCADE
    )]
)
class CachedVideoMetadata(
  var commentsConnection: CachedConnection? = null,
  var likesConnection: CachedConnection? = null
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
  var videoId: Long = 0
}