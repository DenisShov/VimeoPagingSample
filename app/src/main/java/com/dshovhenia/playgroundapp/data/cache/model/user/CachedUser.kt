package com.dshovhenia.playgroundapp.data.cache.model.user

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.dshovhenia.playgroundapp.data.cache.db.DbConstants
import com.dshovhenia.playgroundapp.data.cache.model.comment.CachedComment
import com.dshovhenia.playgroundapp.data.cache.model.pictures.CachedPictures
import com.dshovhenia.playgroundapp.data.cache.model.video.CachedVideo

@Entity(
  tableName = DbConstants.USER_TABLE_NAME,
  foreignKeys = [
    ForeignKey(
      entity = CachedComment::class,
      parentColumns = ["uri"],
      childColumns = ["commentId"],
      onDelete = CASCADE
    ),
    ForeignKey(
      entity = CachedVideo::class,
      parentColumns = ["uri"],
      childColumns = ["videoId"],
      onDelete = CASCADE
    )]
)
class CachedUser(
  var name: String = "",
  var pictures: CachedPictures?,
  var userMetadata: CachedUserMetadata?
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0
  var commentId: Long = 0
  var videoId: Long = 0
}
