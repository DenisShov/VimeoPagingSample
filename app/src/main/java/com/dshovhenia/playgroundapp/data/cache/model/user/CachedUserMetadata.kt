package com.dshovhenia.playgroundapp.data.cache.model.user

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.connection.CachedConnection

@Entity(
  tableName = DbConstants.USER_METADATA_TABLE_NAME,
  foreignKeys = [
    ForeignKey(
      entity = CachedUser::class,
      parentColumns = ["id"],
      childColumns = ["userId"],
      onDelete = ForeignKey.CASCADE
    )]
)
class CachedUserMetadata(
  var followersConnection: CachedConnection? = null,
  var videosConnection: CachedConnection? = null
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
  var userId: Long = 0
}