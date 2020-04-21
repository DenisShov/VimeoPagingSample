package com.dshovhenia.playgroundapp.data.cache.model.video

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants

@Entity(
  tableName = DbConstants.VIDEO_STATS_TABLE_NAME,
  foreignKeys = [
    ForeignKey(
      entity = CachedVideo::class,
      parentColumns = ["uri"],
      childColumns = ["videoId"],
      onDelete = ForeignKey.CASCADE
    )]
)
class CachedVideoStats(
  var plays: Long = 0
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
  var videoId: Long = 0
}