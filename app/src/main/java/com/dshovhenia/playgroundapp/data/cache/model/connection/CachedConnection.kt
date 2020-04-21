package com.dshovhenia.playgroundapp.data.cache.model.connection

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.user.CachedUserMetadata
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideoMetadata

@Entity(
  tableName = DbConstants.CONNECTION_TABLE_NAME,
  foreignKeys = [
    ForeignKey(
      entity = CachedUserMetadata::class,
      parentColumns = ["id"],
      childColumns = ["userMetadataId"],
      onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
      entity = CachedVideoMetadata::class,
      parentColumns = ["id"],
      childColumns = ["videoMetadataId"],
      onDelete = ForeignKey.CASCADE
    )]
)
class CachedConnection(
  var uri: String = "",
  var total: Int = 0
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
  var userMetadataId: Long = 0
  var videoMetadataId: Long = 0
}